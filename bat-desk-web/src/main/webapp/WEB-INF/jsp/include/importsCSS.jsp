<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page isELIgnored="false" %>

<%@ taglib prefix="spring" uri="/WEB-INF/tld/spring.tld"  %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"  %>

<link rel="stylesheet" href="${pageContext.request.contextPath}/<spring:theme code="style"/>" type="text/css" />
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/common.css" type="text/css" />

<c:set var="themeCondition"><spring:theme code="style"/></c:set>

<c:choose>
    <c:when test="${themeCondition == 'css/light.css'}">
       <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    </c:when>
    <c:otherwise>
       <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootswatch/3.3.6/cyborg/bootstrap.min.css">
    </c:otherwise>
</c:choose>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.2/jquery.min.js"></script>
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"></script>
