<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: Nazar
  Date: 05.05.2020
  Time: 09:56
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>

<fmt:setLocale value="${language}"/>

<div class="form-popup" id="reg">
    <form action="${pageContext.request.contextPath}/register" method="post" class="form-container">
        <fmt:bundle basename="staticInformation" prefix="login.">
            <h1><fmt:message key="reg"/></h1>
            <p style="color: red;">${errorReg}</p>
            <br/>
            <label for="email"><b><fmt:message key="email"/></b></label>
            <input type="text" placeholder="<fmt:message key="email_placeholder"/>" id="email" name="email" required>

            <label for="f_name"><b><fmt:message key="f_name"/></b></label>
            <input type="text" placeholder="<fmt:message key="f_name_placeholdere"/>" id="f_name" name="f_name" required>

            <label for="l_name"><b><fmt:message key="l_name"/></b></label>
            <input type="text" placeholder="<fmt:message key="l_name_placeholdere"/>" id="l_name" name="l_name" required>

            <label for="psw"><b><fmt:message key="pass"/></b></label>
            <input type="password" placeholder="<fmt:message key="pass_placeholder"/>" id="psw" name="password"
                   required>

            <button type="submit" class="btn"><fmt:message key="button.reg"/></button>
            <button type="button" class="btn cancel" onclick="closeRegForm()"><fmt:message key="button.close"/></button>
        </fmt:bundle>
    </form>
</div>


</body>
</html>
