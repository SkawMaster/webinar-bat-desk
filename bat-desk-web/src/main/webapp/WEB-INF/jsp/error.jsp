<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page isELIgnored="false" %>

<%@ taglib prefix="spring" uri="/WEB-INF/tld/spring.tld"  %>

<!doctype html>
<html>
	<head>
		<meta charset="utf-8">
    	<title><spring:message code="view.error" /></title>
    	<meta name="viewport" content="width=device-width, initial-scale=1.0"> 
    	<jsp:include page="./include/importsCSS.jsp" /> 
    </head>
	<body>
	
		<jsp:include page="./include/header.jsp" >
			<jsp:param name="view" value="villain/edit" />
		</jsp:include>
		
		<jsp:include page="./include/navigation.jsp" />
		
		<h2><spring:message code="view.error" /></h2>
		
		<p>${exception}</p>
		
		<p>${error}</p>
		
		<jsp:include page="./include/footer.jsp" />
		
	</body>
</html>