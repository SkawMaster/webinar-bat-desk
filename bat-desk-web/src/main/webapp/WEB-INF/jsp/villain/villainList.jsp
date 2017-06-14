<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page isELIgnored="false" %>

<%@ taglib prefix="c" uri="/WEB-INF/tld/c.tld"  %>
<%@ taglib prefix="spring" uri="/WEB-INF/tld/spring.tld"  %>

<!doctype html>
<html>
	<head>
		<meta charset="utf-8">
    	<title><spring:message code="villain.title.management" /></title>
    	<meta name="viewport" content="width=device-width, initial-scale=1.0"> 
    	<jsp:include page="../include/importsCSS.jsp" /> 
    </head>
	<body>
	
		<jsp:include page="../include/header.jsp" >
			<jsp:param name="view" value="home" />
		</jsp:include>
	
		<jsp:include page="../include/navigation.jsp" />
	
	   <div class="container">
			<h2 class="logo"><spring:message code="villain.title.list" /></h2>
					
		   <jsp:include page="../include/notification.jsp" />
			
			<c:choose>
  			<c:when test="${empty list}">
     			<p><spring:message code="validation.noData" /></p>
  			</c:when>
  			<c:otherwise>
				<table class="table table-hover">
	            <thead>
	                <tr>
	                    <td><spring:message code="villain.field.id" /></td>
	                    <td><spring:message code="villain.field.name" /></td>
	                    <td><spring:message code="villain.field.status" /></td>
	                    <td class="pull-right"><spring:message code="villain.field.operations" /></td>
	                </tr>
	            </thead>
	            <tbody>
	              <c:forEach items="${list}" var="villain">
	                <tr>
	                    <td>${villain.id}</td>
	                    <td>${villain.name}</td>
	                    <td>
								<jsp:include page="./include/villainStatus.jsp" >
									<jsp:param name="status" value="${villain.status}" />
									<jsp:param name="showIcon" value="true" />
								</jsp:include>
						</td>
	                    <td>
	                        <span class="pull-right">
		                        <a id="detain_${villain.id}" href="${pageContext.request.contextPath}/villain/detain?id=${villain.id}" 
		                         class="btn btn-default" role="button"><span class="glyphicon glyphicon-stop"></span> <spring:message code="villain.action.detain" /></a>
	
		                        <a id="edit_${villain.id}" href="${pageContext.request.contextPath}/villain/showEdit?id=${villain.id}" 
		                         class="btn btn-default" role="button"><span class="glyphicon glyphicon-pencil"></span> <spring:message code="villain.action.edit" /></a>
	
		                        <a id="delete_${villain.id}" href="${pageContext.request.contextPath}/villain/delete?id=${villain.id}" 
		                         class="btn btn-danger" role="button"><span class="glyphicon glyphicon-trash"></span> <spring:message code="villain.action.delete" /></a>
	                        </span>    
	                    </td>
	                </tr>
	                </c:forEach>
	                <tr>
                    <td colspan="6">
                        <a href="${pageContext.request.contextPath}/villain/showAdd"
                        class="btn btn-primary btn-lg" role="button"><span class="glyphicon glyphicon-plus"></span> <spring:message code="villain.action.add" /></a> 
                    </td>
                </tr>
	          </tbody>
	        </table>
	        </c:otherwise>
	        </c:choose>
    </div>

	<jsp:include page="../include/footer.jsp" />
		
	</body>
</html>