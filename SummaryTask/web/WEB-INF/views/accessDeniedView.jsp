<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: Nazar
  Date: 22.03.2020
  Time: 13:49
  To change this template use File | Settings | File Templates.
--%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Access Denied</title>
</head>
<body>

<jsp:include page="_header.jsp"/>

<fmt:setLocale value="${language}"/>

<br/><br/>

<h3 style="color:red;">Access Denied!</h3>
<jsp:include page="_footer.jsp"/>

</body>
</html>