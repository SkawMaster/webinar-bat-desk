<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page isELIgnored="false" %>

<%@ taglib prefix="spring" uri="/WEB-INF/tld/spring.tld"  %>

<li class="dropdown">
   <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><spring:message code="theme.text" /><span class="caret"></span></a>
   <ul class="dropdown-menu" role="menu">
     <li><a href="${pageContext.request.contextPath}/${param.view}?theme=light"><spring:message code="theme.light" /></a></li>
     <li class="divider"></li>
     <li><a href="${pageContext.request.contextPath}/${param.view}?theme=dark"><spring:message code="theme.dark" /></a></li> 
   </ul>
</li>
    
    