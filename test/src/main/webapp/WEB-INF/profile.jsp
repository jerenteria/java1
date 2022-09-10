<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>   
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Profile</title>
</head>
<body>
<h1>${idea.content }</h1>
<h3>Created by: ${idea.creator.name}</h3>
<a href="/edit/${idea.id }">Edit</a>
<form method="post" action="/edit/${idea.id}">
<hr>
<h1>${idea.content }</h1>
<c:if test="${idea.creator.id == user.id}">
<h3>Edit Idea</h3>
<form:form method="POST" action="/addNewIdea" modelAttribute="idea">
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