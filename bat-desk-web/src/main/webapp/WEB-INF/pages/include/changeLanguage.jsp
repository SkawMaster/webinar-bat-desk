<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page isELIgnored="false" %>

<%@ taglib prefix="spring" uri="/WEB-INF/tld/spring.tld"  %>

<li class="dropdown">
	<a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><spring:message code="language.text" /> <span class="caret"></span></a>
	<ul class="dropdown-menu" role="menu">
		<li><a href="${pageContext.request.contextPath}/${param.view}?lang=en_US"><spring:message code="language.english" /></a></li>
		<li class="divider"></li>
		<li><a href="${pageContext.request.contextPath}/${param.view}?lang=es_ES"><spring:message code="language.spanish" /></a></li>
	</ul>
</li>
    