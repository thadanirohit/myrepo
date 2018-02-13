<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" isELIgnored="false" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<h3>This is Project Page</h3>

<div>

<form:form action="saveProject.do" method="POST" modelAttribute="newProject">	
	
	<table>
		<tr>
			<td><form:label path="projectName">Project Name:</form:label></td>
			<td><form:input path="projectName"/></td>
			
			<td><form:label path="clientName">Client Name</form:label></td>
			<td><form:input path="clientName"/></td>
		</tr>
		<tr>
			<td></td>
			<td><form:errors path="projectName" class="error"></form:errors></td>
			
			<td></td>
			<td><form:errors path="clientName" class="error"></form:errors></td>
		</tr>
		
		<tr>
			<td><form:label path="startDate">Start Date:</form:label></td>
			<td><form:input path="startDate"/></td>
			
			<td><form:label path="endDate">End Date:</form:label></td>
			<td><form:input path="endDate"/></td>
		</tr>
		<tr>
			<td></td>
			<td><form:errors path="startDate" class="error"></form:errors></td>
			
			<td></td>
			<td><form:errors path="endDate" class="error"></form:errors></td>
		</tr>
		
	</table>
	
	<form:button>Save</form:button>	
	
</form:form>

<c:set var="prjctSaveMsg" value=""></c:set>

<c:if test="${prjctSaveStatus == 'yes'}">	
	<c:set var="prjctStatusClass" value="success"></c:set>
	<c:set var="prjctSaveMsg" value="Project Saved Successfully ... !!!"></c:set>
</c:if>

<c:if test="${prjctSaveStatus == 'no' || prjctSaveStatus == 'error' }">	
	<c:set var="prjctStatusClass" value="failure"></c:set>
	<c:set var="prjctSaveMsg" value="Project not saved, please try again ... !!!"></c:set>
</c:if>

<div class='<c:out value="${prjctStatusClass}"></c:out>' >
	<c:out value="${prjctSaveMsg}"></c:out>
</div>

</div>

<hr>

<div>

<c:if test="${areProjectAvailable == 'yes'}">
	
	<c:set var="counter" value="1" ></c:set>
	
	<table>
		
		<tr>
			<th>SNo</th>
			<th>Project Name</th>
			<th>Client Name</th>
			<th>Start Date</th>
			<th>End Date</th>
			<th>Action</th>
		</tr>		
	
		<c:forEach items="${projectList}" var="project" >
			<form:form method="POST" modelAttribute="newProject">
			<tr class="readOnly" >
				<td>
					<%-- <c:out value="${counter}"></c:out> --%>
					<form:input path="projectId" value="${project.projectId}" readonly="true" />
				</td>
				<td>
					<%-- <c:out value="${project.projectName}"></c:out> --%>
					<form:input path="projectName" value="${project.projectName}" readonly="true" />
				</td>
				<td>
					<%-- <c:out value="${project.clientName}"></c:out> --%>
					<form:input path="clientName" value="${project.clientName}" readonly="true" />
				</td>
				<td>
					<%-- <c:out value="${project.startDate}"></c:out> --%>
					<form:input path="startDate" value="${project.startDate}" readonly="true" />
				</td>
				<td>
					<%-- <c:out value="${project.endDate}"></c:out> --%>
					<form:input path="endDate" value="${project.endDate}" readonly="true" />
				</td>
				<td class="action">
					<button class='actionBtn editProject' >Edit</button>
					<button class='actionBtn updateProject' >Update</button>
					<button data-projectId='<c:out value="${project.projectId}"></c:out>' class='actionBtn delProject' >Delete</button>
				</td>
			</tr>
			</form:form>
			
			<c:set var="counter" value="${counter+1}" ></c:set>
			
		</c:forEach>
	
	</table>
</c:if>

<c:if test="${areProjectAvailable == 'no' }">	
	<c:out value="No Projects Available"></c:out>
	<c:out value="${projectList}"></c:out>
</c:if>

</div>