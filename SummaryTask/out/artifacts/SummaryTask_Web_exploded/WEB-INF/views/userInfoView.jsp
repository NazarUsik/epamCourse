<%--
  Created by IntelliJ IDEA.
  User: Nazar
  Date: 21.03.2020
  Time: 19:26
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/userAccStyle.css"/>
    <title>Account</title>
</head>
<style>
    table {
        border-collapse: collapse;
        border-spacing: 0;
        width: 100%;
        border: 1px solid #ddd;
    }

    th, td {
        text-align: left;
        padding: 16px;
    }

    tr:nth-child(even) {
        background-color: rgba(242, 242, 242, 0.56);
    }
</style>
<body>

<jsp:include page="_header.jsp"/>

<fmt:setLocale value="${language}"/>

<div class="parallax">

    <fmt:bundle basename="staticInformation" prefix="user.page.">
        <button class="tablink" onclick="openPage('Profile', this, '#1F294D')" id="defaultOpen"><fmt:message
                key="pr"/></button>
        <button class="tablink" onclick="openPage('Tickets', this, '#1F294D')"><fmt:message key="tick"/></button>
    </fmt:bundle>
    <div id="Profile" class="tabcontent">
        <br>
        <br>

        <div class="user_info">
            <fmt:bundle basename="staticInformation" prefix="admin.page.us.">
                <div class="text"><fmt:message key="f_name"/>: ${user.firstName}</div>
                <div class="text"><fmt:message key="l_name"/>: ${user.lastName}</div>
                <div class="text"><fmt:message key="email"/>: ${user.email}</div>
            </fmt:bundle>
        </div>
    </div>


    <div id="Tickets" class="tabcontent">
        <fmt:bundle basename="staticInformation" prefix="admin.page.">
            <table>
                <tr>
                    <th><fmt:message key="rs.dep_s"/></th>
                    <th><fmt:message key="rs.arr_s"/></th>
                    <th><fmt:message key="rs.dep_t"/></th>
                    <th><fmt:message key="rs.arr_t"/></th>
                    <th><fmt:message key="tick.price"/></th>
                </tr>

                <c:forEach items="${ticketsInfo}" var="pair">
                    <tr>
                        <td>${language == 'en' ? pair.key.key.name : pair.key.key.nameRu}</td>
                        <td>${language == 'en' ? pair.key.value.name : pair.key.value.nameRu}</td>
                        <td>${pair.value.key.departureTime}</td>
                        <td>${pair.value.key.arrivalTime}</td>
                        <td>${pair.value.value}</td>
                    </tr>
                </c:forEach>
            </table>
        </fmt:bundle>
    </div>

    <script>
        function openPage(pageName, elmnt, color) {
            var i, tabcontent, tablinks;
            tabcontent = document.getElementsByClassName("tabcontent");
            for (i = 0; i < tabcontent.length; i++) {
                tabcontent[i].style.display = "none";
            }
            tablinks = document.getElementsByClassName("tablink");
            for (i = 0; i < tablinks.length; i++) {
                tablinks[i].style.backgroundColor = color;
                tablinks[i].style.opacity = "0.30";

            }
            document.getElementById(pageName).style.display = "block";
            elmnt.style.backgroundColor = color;
            elmnt.style.opacity = "0.36";
        }

        // Get the element with id="defaultOpen" and click on it
        document.getElementById("defaultOpen").click();
    </script>

</div>

<jsp:include page="_footer.jsp"/>

</body>
</html>