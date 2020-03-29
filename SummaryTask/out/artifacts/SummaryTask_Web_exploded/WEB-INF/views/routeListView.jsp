<%--
  Created by IntelliJ IDEA.
  User: Nazar
  Date: 22.03.2020
  Time: 20:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Product List</title>
</head>
<style>
    table {
        font-family: arial, sans-serif;
        border-collapse: collapse;
        width: 100%;
    }

    td, th {
        border: 1px solid #dddddd;
        text-align: left;
        padding: 8px;
    }

    tr:nth-child(even) {
        background-color: #dddddd;
    }
</style>
<body>

<jsp:include page="_header.jsp"/>

<h3>Product List</h3>

<p style="color: red;">${errorString}</p>

<table width="400">
    <thead>
    <tr>
        <th>Departure station</th>
        <th>Arrival station</th>
        <th>Departure time</th>
        <th>Arrival time</th>
        <th>Travel time</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <c:forEach items="${routeList}" var="pair">
            <c:choose>
                <c:when test="${pair.key == 'Station1'}">
                    <td>${pair.value.name}</td>
                </c:when>
                <c:when test="${pair.key == 'Station2'}">
                    <td>${pair.value.name}</td>
                </c:when>
                <c:when test="${pair.key == 'Schedule'}">
                    <td>${pair.value.departureTime}</td>
                    <td>${pair.value.arrivalTime}</td>
                    <td>${pair.value.travelTime/60}</td>
                </c:when>
            </c:choose>
        </c:forEach>
        <c:if test="${not empty user}">
            <td>
                <a href="buyTicket">Buy</a>
            </td>
        </c:if>
    </tr>
    </tbody>
</table>


<jsp:include page="_footer.jsp"/>

</body>
</html>