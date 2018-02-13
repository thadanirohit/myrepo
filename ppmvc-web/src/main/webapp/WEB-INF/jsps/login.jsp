<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<spring:message code="lbl.login.title" ></spring:message>

<form:form modelAttribute="user" action="checkLogin.do" >
	
	<table>
	
		<tr>
			<td><form:label path="emailId"><spring:message code="lbl.login.username" ></spring:message>:</form:label></td>
			<td><form:input path="emailId"/></td>
			<td><form:errors path="emailId" class="error"></form:errors></td>
		</tr>
		
		<tr>
			<td><form:label path="password"><spring:message code="lbl.login.password" ></spring:message>:</form:label></td>
			<td><form:password path="password"/></td>
			<td><form:errors path="password" class="error"></form:errors></td>
		</tr>
	
	</table>

	<form:button value="Login"><spring:message code="lbl.login.button" ></spring:message></form:button>
	
</form:form>

<c:set var="loginStatusMsg" value=""></c:set>

<c:if test="${loginStatus == 'valid'}">	
	<c:set var="loginClass" value="success"></c:set>
	<c:set var="loginStatusMsg" value="Login Successfull ... !!!"></c:set>
</c:if>

<c:if test="${loginStatus == 'invalid' }">	
	<c:set var="loginClass" value="failure"></c:set>
	<c:set var="loginStatusMsg" value="Invalid Username / Password ... !!!"></c:set>
</c:if>

<div class='<c:out value="${loginClass}"></c:out>' >
	<c:out value="${loginStatusMsg}"></c:out>
</div>