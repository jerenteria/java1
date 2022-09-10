<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Create Idea</title>
<link rel= "stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="container">
<h1>Welcome ${user.name }</h1>
<hr>
<table class="table table-dark table-hover">
<thead>
<tr>
<th>Idea</th>
<th>Created By</th>
<th>Likes</th>
<th>Action</th>
</tr>
</thead>


<tbody>
<c:forEach items="${ideas}" var="idea">
<tr>
<td><a href="/ideas/${idea.id }">${idea.content }</a></td>
<td>${user.name}</td>
<td>
<c:choose>
<c:when test="${idea.likers.contains(user) }">
<a href="/unlike/${idea.id }">Unlike</a>
</c:when>
<c:otherwise>
<td><a href="/like/${idea.id }">Like</a></td>
</c:otherwise>
</c:choose>
</td>



</c:forEach>


</tbody>

</table>
</div>
<a href="/ideas/new">Create an idea</a>
</body>
</html>