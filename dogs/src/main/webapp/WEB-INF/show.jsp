<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>   
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Dog Detail Page</title>

<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">

</head>
<body>

<div class="container">
<h1>Details For ${dog.name}</h1>
<hr>
<h3>Owner:</h3>
<a href="/user/${user.id}">${dog.owner.firstName}</a></p>
<h3>Name:</h3>
<p>${dog.name}</p>
<h3>Breed:</h3>
<p>${dog.breed }</p>
<h3>Liked By:</h3>
<c:choose>
<c:when test="${dog.likers == null }">
<p>No likes, yet!</p>
</c:when>
<c:otherwise>
<ol>
<c:forEach items="${dog.likers}" var="user">
<li>${user.firstName} ${user.lastName}
</c:forEach>
</ol>
</c:otherwise>
</c:choose>


<h2>Toys</h2>
<ol>
<c:forEach items="${dog.toys }" var="toy">
	<li>${toy.name} - ${toy.description} (${toy.price })</li>
	<h1>Rate This Toy</h1>
	<form:form method="POST" action="/toys/rate/${toy.id}" modelAttribute="rating">
	<p>
		<form:label path="rating">Leave A Rating (1-10)
		<form:input path="rating"/></form:label>
	</p>
	<input type="submit" value="Rate!">
	
	</form:form>
</c:forEach>

</ol>

<c:choose>
<c:when test="${dog.tag != null }">
<h3>City:</h3>
<p>${dog.tag.city}</p>
<h3>State:</h3>
<p>${dog.tag.state }</p>
</c:when>
<c:otherwise>
<form:form method="POST" action="/addTag" modelAttribute="tag">
<div class="form-group">
    <form:label path="city">city
    <form:errors path="city"/>
    <form:input path="city"/></form:label>
</div>
<div class="form-group">
    <form:label path="state">State
    <form:errors path="state"/>
    <form:textarea path="state"/></form:label>
    </div>
    <form:hidden path="dog" value="${dog.id}"/>
    <button>Register This Pet</button>
</form:form>

</c:otherwise>
</c:choose>
<form method="post" action="/edit/${dog.id}">
<hr>
<c:if test="${dog.owner.id == user.id}">
<h2>Edit Dog</h2>
<form:form method="POST" action="/addNewDog" modelAttribute="dog">
<div class="form-group">
<form:label path="name">Name:
<form:errors path="name"/>
<form:input path="name"/></form:label>
</div>
<div class="form-group">
<form:label path="breed">Breed:
<form:errors path="breed"/>
<form:input path="breed"/></form:label>
</div>
<div class="form-group">
<form:label path="age">Age:
<form:errors path="age"/>
<form:input path="age"/></form:label>
</div>
<button>Update Dog Details</button>
</form:form>
</c:if>
</form>

</div>
</body>
</html>