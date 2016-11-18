<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page isELIgnored="false" %>

<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"  %>
<%@ taglib prefix="spring" uri="/WEB-INF/tld/spring.tld"  %>

<!doctype html>
<html>
	<head>
		<meta charset="utf-8">
    	<title><spring:message code="view.login" /></title>
    	<meta name="viewport" content="width=device-width, initial-scale=1.0"> 
    	<jsp:include page="./include/importsCSS.jsp" /> 
    </head>
	<body>

		<jsp:include page="./include/header.jsp" >
			<jsp:param name="view" value="home" />
		</jsp:include>
	
		<jsp:include page="./include/navigation.jsp" />
		
		<div class="container">

			<h2 class="logo"><spring:message code="view.login" /></h2>
		
			<div id="underConstruction" class="underConstruction">
				<img src="<c:url value="/images/logo/under_construction.png"/>" alt="Batman Under Construction Logo" class="imgCenter logoUnderConstruction" />
			</div>	
		
		</div>
		
		<jsp:include page="./include/footer.jsp" />
		
	</body>
</html>