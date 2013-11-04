package com.epam.xmlapp.presentation.facade;

import java.util.List;
import java.util.Map;

import org.jdom2.Document;

import com.epam.xmlapp.model.Category;
import com.epam.xmlapp.parser.XMLParser;

public interface ProductFacade 
{
	XMLParser getParser();
	void setParser(XMLParser parser);
	void setParser(String parserName);
	List<Category> parse();
	String transformTemplate(int template, Map<String, String> params);
	String addNewProduct(int template, Map<String, String> params);
	Document getJdomDocument();
	Document updateElements(int categoryNumber, int subcategoryNumber, Document doc, Integer[] indexes);
	Integer[] getNotInStockIndexes(Document doc, String categoryName, String subcategoryName);
}
