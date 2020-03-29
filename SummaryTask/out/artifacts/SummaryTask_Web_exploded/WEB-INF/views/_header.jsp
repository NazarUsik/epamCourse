<%--
  Created by IntelliJ IDEA.
  User: Nazar
  Date: 21.03.2020
  Time: 10:33
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/style.css">
<body>

<style>

    .logoImg {
        margin: 12px 450px;
        padding: 10px 20px;
    }


    .loginBtn {
        line-height: 32px;
        background-color: #fff;
        border: 1px solid black;
        border-radius: 5px;
        color: #000;
        float: right;
        height: 4px;
        margin: 15px 20px;
        padding: 7px 20px 33px 15px;
        text-transform: uppercase;
        width: auto;
    }
</style>

<div class="navbar">
    <span id="menuBtn" onclick="openNav()">&#9776; menu</span>

    <a class="logoImg" href="${pageContext.request.contextPath}/">
        <img src="${pageContext.request.contextPath}/images/white_logo.png" alt="logo.png" width="296" height="80">
    </a>

    <c:choose>
        <c:when test="${empty loginedUser}">
            <button class="loginBtn" onclick="openForm()">Login</button>
            <%--            <span class="loginBtn" onclick="document.getElementById('id01').style.display='block'">Login</span>--%>
        </c:when>
        <c:otherwise>
            <c:choose>
                <c:when test="${logoutParam == 'exit'}">
                    <a class="loginBtn" href="${pageContext.request.contextPath}/logout">Logout</a>
                </c:when>
                <c:otherwise>
                    <a class="loginBtn" href="${pageContext.request.contextPath}/userInfo">My</a>
                </c:otherwise>
            </c:choose>
        </c:otherwise>
    </c:choose>
</div>


<div id="myNav" class="overlay">
    <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
    <div class="overlay-content">
        <a href="${pageContext.request.contextPath}/">Home</a>
        <a href="${pageContext.request.contextPath}/routeSearch">Search route</a>
        <c:if test="${loginedUser.roleId == 1}">
            <a href="${pageContext.request.contextPath}/addRoute">New route</a>
            <a href="#">New Train</a>
        </c:if>
    </div>
</div>

<script>
    function openNav() {
        document.getElementById("myNav").style.width = "30%";
    }

    function closeNav() {
        document.getElementById("myNav").style.width = "0%";
    }
</script>


<jsp:include page="loginView.jsp"/>

</body>
</html>
