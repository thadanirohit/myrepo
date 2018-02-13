<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<h3>This is Registration page</h3>

<form:form action="doRegister.do" method="POST" modelAttribute="user">

<table>	

	<tr>		
		<td><form:label path="firstName">First Name: </form:label></td>
		<td><form:input path="firstName"/></td>
		<td><form:errors path="firstName" class="error"></form:errors></td>	
	</tr>	
	
	<tr>				
		<td><form:label path="lastName">Last Name: </form:label> </td>
		<td><form:input path="lastName"/></td>
		<td><form:errors path="lastName" class="error"></form:errors> </td>
	</tr>
	
	<tr>
		<td><form:label path="emailId">Email: </form:label></td>
		<td><form:input path="emailId"/></td>
		<td><form:errors path="emailId" class="error"></form:errors></td>	
	</tr>
	
	<tr>				
		<td><form:label path="password">Password: </form:label></td>
		<td><form:password path="password"/></td>
		<td><form:errors path="password" class="error"></form:errors></td>
	</tr>
	
	<tr>				
		<td><form:label path="addresses[0].street">Street: </form:label></td>
		<td><form:input path="addresses[0].street"/></td>		
	</tr>	
	
	<tr>				
		<td><form:label path="addresses[0].city">City: </form:label></td>
		<td><form:input path="addresses[0].city"/></td>		
	</tr>
	
	<tr>				
		<td><form:label path="addresses[0].pinCode">Pincode: </form:label></td>
		<td><form:input path="addresses[0].pinCode"/></td>
	</tr>
	
	<tr>				
		<td><form:label path="addresses[0].state">State: </form:label></td>
		<td><form:input path="addresses[0].state"/></td>		
	</tr>

</table>
	
	<form:button>Register</form:button>

</form:form>

<c:set var="regStatusMsg" value=""></c:set>

<c:if test="${regStatus == 'yes'}">	
	<c:set var="regClass" value="success"></c:set>
	<c:set var="regStatusMsg" value="Registration Successfull ... !!!"></c:set>
</c:if>

<c:if test="${regStatus == 'no' || regStatus == 'error' }">	
	<c:set var="regClass" value="failure"></c:set>
	<c:set var="regStatusMsg" value="Registration failed, please try again ... !!!"></c:set>
</c:if>

<div class='<c:out value="${regClass}"></c:out>' >
	<c:out value="${regStatusMsg}"></c:out>
</div>