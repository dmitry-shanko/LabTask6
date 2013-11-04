package com.epam.xmlapp.presentation.form;

import org.apache.struts.action.ActionForm;
import org.jdom2.Document;

public class JdomParserForm extends ActionForm
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7688000870258091598L;
	
	private Document document;
	private int categoryNumber;
	private int subcategoryNumber;
	private String categoryName;
	private String subcategoryName;
	private Integer[] indexes;

	/**
	 * @return the document
	 */
	public Document getDocument() {
		return document;
	}

	/**
	 * @param document the document to set
	 */
	public void setDocument(Document document) {
		this.document = document;
	}

	/**
	 * @return the categoryName
	 */
	public String getCategoryName() {
		return categoryName;
	}

	/**
	 * @param categoryName the categoryName to set
	 */
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	/**
	 * @return the subcategoryName
	 */
	public String getSubcategoryName() {
		return subcategoryName;
	}

	/**
	 * @param subcategoryName the subcategoryName to set
	 */
	public void setSubcategoryName(String subcategoryName) {
		this.subcategoryName = subcategoryName;
	}

	/**
	 * @return the indexes
	 */
	public Integer[] getIndexes() {
		return indexes;
	}

	/**
	 * @param indexes the indexes to set
	 */
	public void setIndexes(Integer[] indexes) {
		this.indexes = indexes;
	}

	/**
	 * @return the categoryNumber
	 */
	public int getCategoryNumber() {
		return categoryNumber;
	}

	/**
	 * @param categoryNumber the categoryNumber to set
	 */
	public void setCategoryNumber(int categoryNumber) {
		this.categoryNumber = categoryNumber;
	}

	/**
	 * @return the subcategoryNumber
	 */
	public int getSubcategoryNumber() {
		return subcategoryNumber;
	}

	/**
	 * @param subcategoryNumber the subcategoryNumber to set
	 */
	public void setSubcategoryNumber(int subcategoryNumber) {
		this.subcategoryNumber = subcategoryNumber;
	}
	

}
