<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page isELIgnored="false" %>

<%@ taglib prefix="spring" uri="/WEB-INF/tld/spring.tld"  %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld" %>

<c:if test="${not empty exception}">
	<div id="notificationException" class="alert alert-error" >
		<strong><spring:message code="message.error"/>:</strong> ${exception}
	</div>
</c:if>

<c:if test="${not empty message}">
	<div id="notificationMessage" class="alert alert-success">
		<strong><spring:message code="message.info"/>:</strong> ${message}
	</div>
</c:if>