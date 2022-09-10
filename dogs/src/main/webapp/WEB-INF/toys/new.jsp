<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<link rel= "stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="container">
<h2>Give a furry buddy a toy</h2>
<hr>
<form:form method="POST" action="/toys" modelAttribute="toy">

	<div class="form-group">
	<form:label path="dog">Gift This Toy To:</form:label>
	<form:errors path="dog"/>
	<form:select path="dog">
	<c:forEach items="${dogs}" var="dog">
	<option value="${dog.id}">${dog.name}</option>
	</c:forEach>
	</form:select>
	</</div>
	
	    <div class="form-group">
    <form:label path="name">Name
    <form:errors path="name"/>
    <form:input path="name"/></form:label>
    </div>
    <div class="form-group">
    <form:label path="price">Price
    <form:errors path="price"/>
    <form:input  type="decimal" path="price"/></form:label>
    </div>
	<div class="form-group">
    <form:label path="description">Description
    <form:errors path="description"/>
    <form:input path="description"/></form:label>
	</div>
    <button>Gift this Toy!</button>

</form:form>


</div>
</div>
</body>
</html>