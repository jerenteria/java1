<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Welcome To Wedding Dot Com</title>
<link rel= "stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="container">
<h1>Welcome to Wedding Planner</h1>
<a href="/logout">Logout</a> | <a href="/weddings/new">Create Wedding</a>
<hr>
<table class="table table-dark table-hover">
<thead>
<tr>
<th>Wedding</th>
<th>Date</th>
<th>Guests</th>
<th>Action</th>
</tr>
</thead>
<tbody>
<c:forEach items="${weddings}" var="wedding">
<tr>
<td><a href="/weddings/${wedding.id }">${wedding.wedderOne } ${wedding.wedderTwo }</a></td>
<td><fmt:formatDate value="${wedding.date}" pattern="MMM dd, yyyy"/></td>
<td>${wedding.guests.size() }</td>

<c:choose>
<c:when test="${wedding.planner.id == user.id }">
<td><a href="#">Delete</a></td>
</c:when>
<c:when test="${wedding.guests.contains(user) }">
<td><a href="#">Un-RSVP</a></td>
</c:when>
<c:otherwise>
<td><a href="#">RSVP</a></td>
</c:otherwise>
</c:choose>
</tr>


</c:forEach>


</tbody>

</table>
</div>
</body>
</html>