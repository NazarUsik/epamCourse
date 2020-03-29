<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/homeStyle.css">
    <meta charset="UTF-8">
    <title>Home Page</title>
</head>
<body>


<jsp:include page="_header.jsp"/>

<c:if test="${errorString != null}">

    <script>
        openForm()
    </script>

</c:if>

<div class="bgIm">
    <div class="crumb"></div>
</div>


<jsp:include page="_footer.jsp"/>

</body>
</html>