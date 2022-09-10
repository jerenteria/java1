<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<form method="post" action="/edit/${idea.id}">
<hr>
<h1>${idea.content }</h1>
<c:if test="${idea.creator.id == user.id}">
<form:form method="POST" action="/edit/${idea.id}" modelAttribute="idea">
<div class="form-group">
<form:label path="content">Name:
<form:errors path="content"/>
<form:input path="content"/></form:label>
</div>
<button>Update Idea</button>
</form:form>
</c:if>
</form>
</body>
</html>