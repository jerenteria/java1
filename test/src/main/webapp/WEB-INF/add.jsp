<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Create New Idea</title>
<link rel= "stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
</head>
<body>
<div class="container">
  <form:form action="/ideas/new" method="post" modelAttribute="idea">
    <form:input type="hidden" value="${ user_id }" path="creator" />

    <div class="form-group">
          <form:label path="content">Idea</form:label>
          <form:errors path="content"/>
          <form:input class="form-control" path="content" />
    </div>
      <button>Submit</button>
      </form:form>



</div>
</body>
</html>