<%--
  Created by IntelliJ IDEA.
  User: Nazar
  Date: 21.03.2020
  Time: 10:33
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/style.css">
<body>

<c:set var="language" value="${cookie.lang.value != null ? cookie.lang.value : 'en'}" scope="session"/>

<fmt:setLocale value="${language}"/>

<div class="navbar">
    <fmt:bundle basename="staticInformation" prefix="header.button.">
        <span id="menuBtn" onclick="openNav()">&#9776; <fmt:message key="menu"/></span>

        <a class="logoImg" href="${pageContext.request.contextPath}/">
            <img src="${pageContext.request.contextPath}/images/white_logo.png" alt="logo.png" width="296" height="80">
        </a>

        <c:choose>
            <c:when test="${empty loginedUser}">
                <button class="loginBtn" onclick="openForm()"><fmt:message key="login"/></button>
            </c:when>
            <c:otherwise>
                <a class="loginBtn logoutBtn" style="" href="${pageContext.request.contextPath}/logout"><fmt:message
                        key="logout"/></a>
            </c:otherwise>
        </c:choose>

        <c:if test="${language == 'ru'}">
            <form action="${pageContext.request.contextPath}/translate" method="post"
                  style="display: inline;">
                <input type="hidden" name="language" value="ru">
                <button class="translate_button" type="submit">RU</button>
            </form>
        </c:if>
        <c:if test="${language == 'en'}">
            <form action="${pageContext.request.contextPath}/translate" method="post"
                  style="display: inline;">
                <input type="hidden" name="language" value="en">
                <button class="translate_button" type="submit">EN</button>
            </form>
        </c:if>

    </fmt:bundle>
</div>

<div id="myNav" class="overlay">
    <a href="javascript:void(0)" class="closebtn" onclick="closeNav()">&times;</a>
    <div class="overlay-content">
        <fmt:bundle basename="staticInformation" prefix="header.href.">
            <a href="${pageContext.request.contextPath}/"><fmt:message key="home"/></a>
            <a href="${pageContext.request.contextPath}/routeSearch"><fmt:message key="search_route"/></a>
            <c:if test="${loginedUser != null}">
                <a href="${pageContext.request.contextPath}/userInfo"><fmt:message key="account"/></a>
            </c:if>
            <c:if test="${loginedUser.roleId == 1}">
                <a href="${pageContext.request.contextPath}/adminPage"><fmt:message key="admin_page"/></a>
            </c:if>
        </fmt:bundle>
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
