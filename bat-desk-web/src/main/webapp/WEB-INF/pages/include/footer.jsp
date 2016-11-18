<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page isELIgnored="false" %>

<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"  %>
<%@ taglib prefix="spring" uri="/WEB-INF/tld/spring.tld"  %>
<%@ taglib prefix="fmt" uri="/WEB-INF/tld/fmt.tld" %>

<br>

<hr>

<footer>
	<div id="logoFooter">
		<img src="<c:url value="/images/logo/batman_incorporated.png"/>" alt="Batman Incorporated Logo" class="imgCenter logoFooter" />
	</div>	
	<p>&copy; <spring:message code="company.name"/> <spring:message code="app.name"/> <fmt:formatDate value="<%=new java.util.Date()%>" pattern="yyyy" /></p>
</footer>
