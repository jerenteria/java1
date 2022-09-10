<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Welcome</title>
<link rel= "stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="container">
<h1>Welcome to Dogs dot com</h1>
<h2>Welcome ${user.firstName }</h2>
<a href="/add">Add Dog To Database</a> | <a href="/toys/new">Gift a Toy!</a> | <a href="/logout">Logout</a>
<table class="table table-dark">
<thead>
<tr>
<th>Action</th>
<th>Name</th>
<th>Breed</th>
<th>Age</th>
<th>Registered</th>
<th># of likes</th>
</tr>
</thead>
<tbody>


<c:forEach items="${allDogs}" var="dog">
<tr>
<td>
<c:choose>
<c:when test="${dog.likers.contains(user) }">
<a href="/unlike/${dog.id }">Unlike</a>
</c:when>
<c:otherwise>
<td><a href="/like/${dog.id }">Like</a></td>
</c:otherwise>
</c:choose>
</td>
<td><a href="/${dog.id}">${dog.name }</a></td>
<td>${dog.breed }</td>
<td>${dog.age }</td>
<td>
<c:choose>
<c:when test="${dog.tag != null }">
Registered
</c:when>
<c:otherwise>
NotRegistered
</c:otherwise>
</c:choose>
</td>
<td> ${dog.likers.size()}</td>
</tr>
</c:forEach>


</tbody>
</table>
</div>
</body>
</html>