<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-nested" prefix="nested"%>
<%@	taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<span class="contentNewsTitle"><html:link action="/products">
		<bean:message key="label.jdom.products" />
	</html:link></span>
&gt;&gt;
<bean:message key="label.jdom.jdomparser" />
<br>
<br>
<logic:empty name="jdomForm" property="document">
	<bean:message key="label.jdom.noresult" />
</logic:empty>
<logic:notEmpty name="jdomForm" property="document">
	<nested:form action="/jdomupdate.do" method="POST">
		<input type="hidden" id="categoryName"
			value="${jdomForm.categoryName }" />
		<input type="hidden" id="subcategoryName"
			value="${jdomForm.subcategoryName }" />
		<table id="products">
			<nested:root name="jdomForm">
				<nested:nest property="document.rootElement">
					<nested:iterate property="children" indexId="categoryNumber">
						<nested:equal value="${jdomForm.categoryName}"
							property="attributes[0].value">
							<tr>
								<td id="category"><nested:write
										property="attributes[0].value" /></td>
							</tr>
							<nested:iterate property="children" indexId="subcategoryNumber">
								<nested:equal value="${jdomForm.subcategoryName}"
									property="attributes[0].value">
									<input type="hidden" name="categoryNumber"
										value="${categoryNumber}" />
									<input type="hidden" name="subcategoryNumber"
										value="${subcategoryNumber }" />
									<tr>
										<td id="subcategory"><nested:write
												property="attributes[0].value" /></td>
									</tr>
									<tr>
										<td class="product">Name:</td>
										<td class="product">Provider:</td>
										<td class="product">Model:</td>
										<td class="product">Color:</td>
										<td class="product">Date:</td>
										<td class="product">Price:</td>
										<td class="product">Not in stock:</td>
									</tr>
									<nested:size id="productsNumber" property="children" />
									<nested:iterate property="children" indexId="elemId">
										<tr>
											<td><nested:text property="attributes[0].value"
													styleId="${elemId}" styleClass="name" /></td>
											<td><nested:text property="children[0].text"
													styleId="${elemId}" styleClass="provider" /></td>
											<td><nested:text property="children[1].text"
													styleId="${elemId}" styleClass="model" /></td>
											<td><nested:text property="children[2].text"
													styleId="${elemId}" styleClass="color" /></td>
											<td><nested:text property="children[3].text"
													styleId="${elemId}" styleClass="date" /></td>

											<nested:empty property="children[4].text">
												<td><nested:text property="children[4].text"
														disabled="true" styleId="${elemId}" styleClass="price" /></td>
												<td><nested:multibox property="indexes" name="jdomForm"
														value="${elemId}" styleId="${elemId}"
														styleClass="notInStock"
														onclick="priceNotInStockValidate('${elemId}')" /></td>
											</nested:empty>

											<nested:notEmpty property="children[4].text">
												<td><nested:text property="children[4].text"
														styleId="${elemId}" styleClass="price" /></td>

												<td><nested:multibox property="indexes" name="jdomForm"
														value="${elemId}" styleId="${elemId}"
														styleClass="notInStock"
														onclick="priceNotInStockValidate('${elemId}')" /></td>
											</nested:notEmpty>
										</tr>
									</nested:iterate>

								</nested:equal>
							</nested:iterate>
						</nested:equal>
					</nested:iterate>
				</nested:nest>
			</nested:root>
		</table>
		<table>
			<tr>
				<td><input type="button" value="Back"
					onclick="location='jdomviewsubcategories.do?categoryName=${jdomForm.categoryName}'" /></td>
				<td><input type="submit" value="Update"
					onclick="return updateValidate('${productsNumber}')" /></td>
				<td><input type="button" value="ADD"
					onclick="location='jdomaddpage.do?categoryName=${jdomForm.categoryName}&subcategoryName=${jdomForm.subcategoryName}'" /></td>
			</tr>
		</table>
		<ul id="errors" class="error">
		</ul>
	</nested:form>
</logic:notEmpty>
<br>