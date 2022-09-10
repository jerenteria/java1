<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel= "stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="container">
User details for ${user.firstName } ${user.lastName }
<h3>Pets Owned:</h3>
<hr>
<ul>
<c:forEach items="${user.dogs }" var="dog">
<li><a href="${dog.id }">${dog.name }</a></li>
</c:forEach>
</ul>
</div>
</body>
</html>