<%--
  Created by IntelliJ IDEA.
  User: Nazar
  Date: 22.03.2020
  Time: 19:06
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="f" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Search route</title>
</head>
<body>
<jsp:include page="_header.jsp"/>

<jsp:useBean id="now" class="java.util.Date"/>

<form action="${pageContext.request.contextPath}/routeSearch" method="post">
    <label for="stationFrom">From </label>
    <input type="text" id="stationFrom" name="stationFrom" placeholder="Enter origin station..."><br>
    <label for="stationTo">To </label>
    <input type="text" id="stationTo" name="stationTo" placeholder="Enter destination station..."><br>
    <label for="start">Start date </label>
        <input type="date" id="start" name="startDate"
               value="${now}"
               min=<f:formatDate value="${now}" pattern="yyyy-MM-dd"/>><br><br>
    <input type="submit" value="Submit">
</form>

<jsp:include page="_footer.jsp"/>
</body>
</html>
