package com.epam.xmlapp.jdom;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import org.jdom2.output.XMLOutputter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JdomParser 
{
	private static final Logger log = LoggerFactory.getLogger(JdomParser.class);
	private static final Lock lock = new ReentrantLock();
//	private static final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
	private SAXBuilder builder = new SAXBuilder();
	private String xmlFileName;
	private String dtdFileName;
	private String xsdFileName;

	private JdomParser()
	{

	}

	/**
	 * @return the xmlFileName
	 */
	public String getXmlFileName() {
		return xmlFileName;
	}
	/**
	 * @param xmlFileName the xmlFileName to set
	 */
	public void setXmlFileName(String xmlFileName) 
	{
		this.xmlFileName = xmlFileName;
	}
	/**
	 * @return the dtdFileNams
	 */
	public String getDtdFileName() {
		return dtdFileName;
	}
	/**
	 * @param dtdFileNams the dtdFileNams to set
	 */
	public void setDtdFileName(String dtdFileName) 
	{
		this.dtdFileName = dtdFileName;
	}
	/**
	 * @return the xsdFileName
	 */
	public String getXsdFileName() {
		return xsdFileName;
	}
	/**
	 * @param xsdFileName the xsdFileName to set
	 */
	public void setXsdFileName(String xsdFileName) {
		this.xsdFileName = xsdFileName;
	}	

	public Document getDocument()
	{	
		try 
		{
//			lock.readLock().lock();
			lock.lock();
			Document document = builder.build(xmlFileName);
			return document;
		} 
		catch (JDOMException e) 
		{
			log.error("JDOMException catched in {}. Can't create document model for xmlFile={} with JDOM.", getClass(), xmlFileName);
			log.error("Exception: ", e);
		} 
		catch (IOException e) 
		{
			log.error("IOException catched in {}. Can't create document model for xmlFile={} with JDOM.", getClass(), xmlFileName);
			log.error("Exception: ", e);
		}
		finally
		{
//			lock.readLock().unlock();
			lock.unlock();
		}
		return null;
	}

	public Integer[] getIndexes(Document doc, String categoryName, String subcategoryName)
	{
		List<Integer> indexes = new ArrayList<Integer>();
		if ((null != doc) && (null != categoryName) && (null != subcategoryName))
		{
			List<Element> categories = doc.getRootElement().getChildren(ProductsConstants.CATEGORY);
			log.debug("categories={}", categories);
			for (Element category : categories)
			{
				if (categoryName.equals(category.getAttributes().get(0).getValue()))
				{
					List<Element> subcategories = category.getChildren(ProductsConstants.SUBCATEGORY);
					log.debug("subcategories={}", subcategories);
					for (Element subcategory : subcategories)
					{
						if (subcategoryName.equals(subcategory.getAttributes().get(0).getValue()))
						{
							List<Element> products = subcategory.getChildren(ProductsConstants.PRODUCT);
							log.debug("products={}", products);
							for (int i = 0; i < products.size(); i++)
							{
								if (products.get(i).getChild(ProductsConstants.NOT_IN_STOCK) != null)
								{
									indexes.add(i);
								}
							}
						}
					}
				}
			}
		}
		log.debug("indexes={}", indexes);
		return indexes.toArray(new Integer[]{});
	}

	public Document updateElements(int categoryNumber, int subcategoryNumber, Document document, Integer[] indexes)
	{
		if ((categoryNumber >= 0) && (subcategoryNumber >= 0) && (null != document) && (null != indexes))
		{
			try
			{
//				lock.writeLock().lock();	
				lock.lock();
				List<Element> products = document.getRootElement().getChildren(ProductsConstants.CATEGORY).get(categoryNumber).getChildren(ProductsConstants.SUBCATEGORY).get(subcategoryNumber).getChildren(ProductsConstants.PRODUCT);
				for (Integer i : indexes)
				{
					Element curProduct = products.get(i);
					Element param = curProduct.getChild(ProductsConstants.PRICE);
					if (param != null)
					{
						param.setText("");
						param.setName(ProductsConstants.NOT_IN_STOCK);
					}
				}
				for (Element curProduct : products)
				{
					Element param = curProduct.getChild(ProductsConstants.NOT_IN_STOCK);
					if (param != null)
					{
						String value = param.getText();
						if ((value != null) && (value.trim().length() > 0))
						{
							param.setName(ProductsConstants.PRICE);
						}
					}
				}
				for (Element curProduct : products)
				{
					if ((curProduct.getChild(ProductsConstants.PRICE) != null) && (curProduct.getChild(ProductsConstants.NOT_IN_STOCK) != null))
					{
						curProduct.removeChild(ProductsConstants.NOT_IN_STOCK);
					}
				}
				XMLOutputter outputter = new XMLOutputter();
				String content = outputter.outputString(document);
				FileWriter writer = null;
				try
				{
					writer = new FileWriter(xmlFileName);
					log.debug("Attempt to write through JDOM Parser result={}", content);
					writer.write(content);
					writer.flush();
					writer.close();		
				} 
				catch (IOException e) 
				{
					log.error("Can't write file", e);
				}
			}
			finally
			{
//				lock.writeLock().unlock();
				lock.unlock();
			}
		}
		return this.getDocument();
	}
}
