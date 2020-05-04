<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: Nazar
  Date: 16.04.2020
  Time: 13:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/searchStyle.css">
<body>

<fmt:setLocale value="${language}"/>

<div class="bgIm">
    <div class="crumb"></div>

    <div class="search">
        <fmt:bundle basename="staticInformation" prefix="search.">

        <p><fmt:message key="text"/></p>


        <div class="row_line">

            <form action="${pageContext.request.contextPath}/routeSearch" method="post">

                <div class="departure">
                    <input class="field" id="dep_station" type="text" name="dep_station"
                           placeholder="<fmt:message key="dep"/>">
                </div>

                <div class="swap">
                    <span onclick="swap_station()">&#8646;</span>
                </div>

                <div class="arrival">
                    <input class="field" id="arr_station" type="text" name="arr_station"
                           placeholder="<fmt:message key="arr"/>">
                </div>

                <div class="date_start">
                    <input class="field" type="text" name="date" onfocus="this.type='date'" onblur="this.type='text'"
                           placeholder="<fmt:message key="date"/>">
                </div>

                <div class="button_search">
                    <input class="field button_wrapper" type="submit" value="<fmt:message key="button.search"/>">
                </div>

            </form>


        </div>


    </div>

    <div class="crumb"></div>
    </fmt:bundle>
</div>

<script>
    function swap_station() {
        var depStat = document.getElementById('dep_station').value;
        var arrStat = document.getElementById('arr_station').value;

        document.getElementById('arr_station').value = depStat;
        document.getElementById('dep_station').value = arrStat;
    }
</script>

</body>
</html>
