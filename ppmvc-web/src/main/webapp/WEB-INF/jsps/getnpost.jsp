<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	
	<form:form modelAttribute="test" method="post" action="getpost.do" >
		<form:label path="name">Name: </form:label> <form:input path="name"/><form:errors class="error" path="name"></form:errors> <br/>
		<form:label path="address">Address: </form:label> <form:input path="address"/><form:errors class="error" path="address"></form:errors><br/>
		<form:button>Submit</form:button>
	</form:form>
	
</body>
</html>