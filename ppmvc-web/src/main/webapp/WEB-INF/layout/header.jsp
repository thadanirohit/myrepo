<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<spring:message code="lbl.header.title" ></spring:message>

Select Language: <a href="${pageContext.request.contextPath}/login.do?lang=en">English</a> | <a href="${pageContext.request.contextPath}/login.do?lang=hi_IN">Hindi</a>