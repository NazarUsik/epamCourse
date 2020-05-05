<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--<%@ taglib prefix="convert" tagdir="/WEB-INF/lib/tag/convertTextHex.tld" %>--%>
<%--
  Created by IntelliJ IDEA.
  User: Nazar
  Date: 21.03.2020
  Time: 10:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Home Page</title>
</head>
<body>


<jsp:include page="_header.jsp"/>

<fmt:setLocale value="${language}"/>

<c:if test="${errorString != null}">

    <script>
        openForm()
    </script>

</c:if>

<c:if test="${errorReg != null}">

    <script>
        openRegForm()
    </script>

</c:if>

<jsp:include page="_search.jsp"/>


<jsp:include page="_footer.jsp"/>

</body>
</html>