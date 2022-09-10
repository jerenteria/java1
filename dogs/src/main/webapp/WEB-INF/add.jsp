<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Dog</title>
<link rel= "stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="form-group">
<form:form method="POST" action="/addDog" modelAttribute="dog">
<div class="form-group">
<form:label path="name">Name:
<form:errors path="name"/>
<form:input path="name"/></form:label>
</div>
<div class="form-group">
<form:label path="breed">Breed:
<form:errors path="breed"/>
<form:input path="breed"/></form:label>
</div>c
<div class="form-group">
<form:label path="age">Age:
<form:errors path="age"/>
<form:input path="age"/></form:label>
</div>
<button>Add Dog</button>




</form:form>







<hr>
<h2>Old way</h2>
<c:forEach items="${errors }" var="err">

<p>${err }</p>
<form action="/addDog" method="POST">
<div class="form-group">
<label for="name">Name</label>
<input type="text" name="name">
</div>
<div class="form-group">
<label for="name">Breed</label>
<input type="text" name="breed">
</div>
<div class="form-group">
<label for="name">Age</label>
<input type="text" name="age">
</div>
<button>Add New Dog</button>




</form>
</c:forEach>
</div>
</body>
</html>