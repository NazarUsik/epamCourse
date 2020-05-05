<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: Nazar
  Date: 21.03.2020
  Time: 19:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/loginStyle.css">

<fmt:setLocale value="${language}"/>

<div class="form-popup" id="myForm">
    <form action="${pageContext.request.contextPath}/login" method="post" class="form-container">
        <fmt:bundle basename="staticInformation" prefix="login.">
            <h1><fmt:message key="login"/></h1>
            <p style="color: red;">${errorString}</p>
            <br/>
            <label for="email"><b><fmt:message key="email"/></b></label>
            <input type="text" placeholder="<fmt:message key="email_placeholder"/>" id="email" name="email" required>

            <label for="psw"><b><fmt:message key="pass"/></b></label>
            <input type="password" placeholder="<fmt:message key="pass_placeholder"/>" id="psw" name="password"
                   required>

            <label>
                <input type="checkbox" name="rememberMe" value="Y"> <fmt:message key="remember"/>
            </label>

            <button type="submit" class="btn"><fmt:message key="button.login"/></button>
            <button type="button" onclick="openRegForm()" class="btn"><fmt:message key="button.reg"/></button>
            <button type="button" class="btn cancel" onclick="closeForm()"><fmt:message key="button.close"/></button>
        </fmt:bundle>
    </form>
</div>

<jsp:include page="registerView.jsp"/>

<script>
    function openForm() {
        document.getElementById("myForm").style.display = "block";
    }

    function closeForm() {
        document.getElementById("myForm").style.display = "none";
    }

    function openRegForm() {
        document.getElementById("reg").style.display = "block";
        closeForm();
    }

    function closeRegForm() {
        document.getElementById("reg").style.display = "none";
        openForm();
    }
</script>
