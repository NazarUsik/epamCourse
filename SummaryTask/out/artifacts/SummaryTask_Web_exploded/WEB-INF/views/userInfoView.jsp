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
<body>

<jsp:include page="_header.jsp"/>

<fmt:setLocale value="${language}"/>

<div class="parallax">

    <fmt:bundle basename="staticInformation" prefix="user.page.">
        <button class="tablink" onclick="openPage('Profile', this, '#1F294D')" id="defaultOpen"><fmt:message
                key="pr"/></button>
        <button class="tablink" onclick="openPage('Profile', this, '#1F294D')"><fmt:message key="tick"/></button>
        <button class="tablink" onclick="openPage('Profile', this, '#1F294D')"><fmt:message key="st"/></button>
    </fmt:bundle>
    <div id="Profile" class="tabcontent">
        <br>
        <br>

        <h3>Hello: ${user.firstName} ${user.lastName}</h3>

        <p>User Name: <b>${user.firstName} ${user.lastName}</b></p>
        <br/>
        <p> Email: ${user.email} <br/></p>
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