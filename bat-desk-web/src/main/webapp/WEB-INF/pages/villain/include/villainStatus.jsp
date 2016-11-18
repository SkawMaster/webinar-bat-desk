<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page isELIgnored="false" %>
 
<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"  %>
<%@ taglib prefix="spring" uri="/WEB-INF/tld/spring.tld"  %>

<c:set var="showIcon" value="${param.showIcon}"/>
<c:if test="${empty showIcon}">
   <c:set var="showIcon" value="false"/>
</c:if>

<div id="villainStatus" class="villainStatus">
  
	<c:choose>
	    <c:when test="${param.status == 'DETAINED'}">
	    	<c:if test="${showIcon}">
	    		<img src="<c:url value="/images/detenido.png"/>" alt="Detained Status Icon" class="statusIcon" /> 
	    	</c:if>
	    	<spring:message code="villain.status.DETAINED" />
	    </c:when>
	    <c:when test="${param.status == 'DEAD'}">
	        <c:if test="${showIcon}">
	        	<img src="<c:url value="/images/muerto.png"/>" alt="Dead Status Icon" class="statusIcon" />
	        </c:if> 
	        <spring:message code="villain.status.DEAD" />
	    </c:when>
	    <c:when test="${param.status == 'FREE'}">
	    	<c:if test="${showIcon}">
	        	<img src="<c:url value="/images/libre.png"/>" alt="Free Status Icon" class="statusIcon" />
	        </c:if>  
	        <spring:message code="villain.status.FREE" />
	    </c:when>
	    <c:otherwise>
	    	<c:if test="${showIcon}">
	    		<img src="<c:url value="/images/status_yellow.png"/>" alt="Unknow Status Icon" class="statusIcon" /> 	 
	    	</c:if> 
	    	<spring:message code="villain.status.UNKNOW" />
	    </c:otherwise>
	</c:choose>

</div>
