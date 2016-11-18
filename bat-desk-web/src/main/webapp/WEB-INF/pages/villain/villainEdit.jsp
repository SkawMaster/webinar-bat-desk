<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page isELIgnored="false" %>

<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"  %>
<%@ taglib prefix="spring" uri="/WEB-INF/tld/spring.tld"  %>
<%@ taglib prefix="form" uri="/WEB-INF/tld/spring-form.tld"%>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>

<!doctype html>
<html>
	<head>
		<meta charset="utf-8">
    	<title><spring:message code="villain.title.edit" /></title>
    	<meta name="viewport" content="width=device-width, initial-scale=1.0"> 
    	<jsp:include page="../include/importsCSS.jsp" /> 
    </head>
	<body>
	
		<jsp:include page="../include/header.jsp" >
			<jsp:param name="view" value="home" />
		</jsp:include>
	
		<jsp:include page="../include/navigation.jsp" />
		
		<div class="container">
			<h2 class="logo"><spring:message code="villain.title.edit" /></h2>
		
			<form:form id="editVillainForm" action="${pageContext.request.contextPath}/villain/edit" method="POST" modelAttribute="villain">
				<form:hidden path="id" />
			
				<table>
			        <tr>
			            <td><form:label path="name"><spring:message code="villain.field.name" />:</form:label></td>
			            <td><form:input path="name" cssErrorClass="formFieldError" size="32" maxlength="25" class="form-control"/></td>
			            <td>* <form:errors path="name" /></td>
			        </tr>
			        <tr>
			            <td><form:label path="description"><spring:message code="villain.field.description" />:</form:label></td>
			            <td><form:textarea path="description" cssErrorClass="formFieldError" rows="4" cols="30" class="form-control"/></td>
			            <td><form:errors path="description" /></td>
			        </tr>
			        <tr>
			            <td><form:label path="status"><spring:message code="villain.field.status" />:</form:label></td>
			            <td>
				            <form:select path="status" id="status" class="form-control">
						        <form:option value=""><spring:message code="villain.status.select.text" /></form:option>
						        <c:forEach items="${villainStatus}" var="villainStatus" varStatus="varstatus">
						        	<form:option value="${villainStatus}"><spring:message code="villain.status.${villainStatus}" /></form:option>
	    						</c:forEach>
				
						    </form:select>
			            </td>
			            <td><form:errors path="status" /></td>
			        </tr>
			        <tr>
			            <td><spring:message code="villain.field.creationDate" />:</td>
			            <td><fmt:formatDate type="both" dateStyle="short" timeStyle="short" value="${villain.creationDate}" /></td>
			        </tr>
			        <tr>
			            <td><spring:message code="villain.field.modificationDate" />:</td>
			            <td><fmt:formatDate type="both" dateStyle="short" timeStyle="short" value="${villain.modificationDate}" /></td>
			        </tr>
			        <tr>
			            <td colspan="3">
			            	<button class="btn btn-primary btn-lg" role="button" type="submit"><spring:message code="villain.action.edit" /></button>
			            </td>
			        </tr>
			    </table>
		
			</form:form>
		</div>
		<jsp:include page="../include/footer.jsp" />
		
	</body>
</html>