<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
	<title><tiles:insertAttribute name="title" ignore="true" /></title>
	<link rel="stylesheet" type="text/css" href="css/style.css">
	<script type="text/javascript" src="js/jquery-3.2.1.min.js" ></script>
	<script type="text/javascript" src="js/main.js" ></script>
</head>
<body>

  <div>
  	<tiles:insertAttribute name="header" />
  </div>
  
  	<hr>
  
  <div>
  	<tiles:insertAttribute name="menu" />
  </div>
  	
  	<hr>
  
  <div>
  	<tiles:insertAttribute name="body" />
  </div>
  
  	<hr>
  
  <div>
  	<tiles:insertAttribute name="footer" />
  </div>
  
</body>
</html>
