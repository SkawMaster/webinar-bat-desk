<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page isELIgnored="false" %>

<%@ taglib prefix="spring" uri="/WEB-INF/tld/spring.tld"  %>


<nav class="navbar navbar-default">
     <div class="container-fluid">
         <div id="titleHeader" class="navbar-header">
             <a class="navbar-brand logo" href="#"><spring:message code="app.name" /></a>
         </div>
         <ul class="nav navbar-nav">
             <li><a id="navigation_home" href="${pageContext.request.contextPath}/home"><spring:message code="view.home" /></a></li>
             <li><a id="navigation_login" href="${pageContext.request.contextPath}/login"><spring:message code="view.login" /> (<spring:message code="message.underConstruction" />)</a></li>
             <li><a id="navigation_villain_list" href="${pageContext.request.contextPath}/villain/list"><spring:message code="villain.title.management" /></a></li>
             
          	<jsp:include page="changeLanguage.jsp">
			   <jsp:param name="view" value="${param.view}" />
		    </jsp:include>
		    
		    <jsp:include page="changeTheme.jsp">
			   <jsp:param name="view" value="${param.view}" />
		    </jsp:include>
		    
        </ul>
     </div>
</nav>

<br>
		  