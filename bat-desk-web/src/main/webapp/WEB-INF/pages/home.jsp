<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page isELIgnored="false" %>

<%@ taglib prefix="spring" uri="/WEB-INF/tld/spring.tld"  %>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"  %>

<!doctype html>
<html>
	<head>
		<meta charset="utf-8">
    	<title><spring:message code="view.home" /></title>
    	<meta name="viewport" content="width=device-width, initial-scale=1.0">
    	<jsp:include page="./include/importsCSS.jsp" /> 
    </head>
	<body>
		
		<jsp:include page="./include/header.jsp" >
			<jsp:param name="view" value="home" />
		</jsp:include>
		
		<jsp:include page="./include/navigation.jsp" />
		
		<div class="container">
			<h2 class="logo"><spring:message code="view.home" /></h2>
			
			<div id="homeContent" >
				<img src="<c:url value="/images/logo/batman_logo.png"/>" alt="Batman Logo" class="imgCenter logoHome" />
				<p><spring:message code="view.connection" /><fmt:formatDate type="both" dateStyle="medium" timeStyle="medium" value="${connectionTime}" /></p>
			</div>	
		
		</div>
		
		<jsp:include page="./include/footer.jsp" />
		
	</body>
</html>