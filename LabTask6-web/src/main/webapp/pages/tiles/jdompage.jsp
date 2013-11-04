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
	<table>
		<nested:root name="jdomForm">
			<nested:nest property="document.rootElement">
				<nested:iterate property="children" indexId="categoryNumber">
					<bean:define id="categoryProductsNumber" value="0" />
					<nested:iterate property="children">
						<nested:size id="subcategoryProductsNumber" property="children" />
						<bean:define id="categoryProductsNumber"
							value="${categoryProductsNumber + subcategoryProductsNumber }" />
					</nested:iterate>
					<nested:define id="categoryName" property="attributes[0].value" />
					<tr>
						<td><html:link
								action="/jdomviewsubcategories.do?categoryName=${categoryName }">
								<nested:write property="attributes[0].value" />
								<nested:messagesNotPresent>(${categoryProductsNumber})</nested:messagesNotPresent>
							</html:link></td>
					</tr>
				</nested:iterate>
			</nested:nest>
		</nested:root>
	</table>
</logic:notEmpty>
<br>