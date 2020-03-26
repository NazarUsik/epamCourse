<%--
  Created by IntelliJ IDEA.
  User: Nazar
  Date: 21.03.2020
  Time: 19:26
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>User Info</title>
</head>
<body>

<jsp:include page="_header.jsp"/>

<h3>Hello: ${user.firstName} ${user.lastName}</h3>

User Name: <b>${user.firstName} ${user.lastName}</b>
<br/>
Email: ${user.email} <br/>

<c:if test="${user.roleId == 1}">
    <a href="adminPage">Go to admin page</a>
</c:if>
<form action="${pageContext.request.contextPath}/logout" method="get">
    <input type="submit" name="Submit">
</form>
<jsp:include page="_footer.jsp"/>

</body>
</html>