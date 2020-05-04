<%@ page import="ua.nure.usik.SummaryTask4.db.DBManager" %>
<%@ page import="ua.nure.usik.SummaryTask4.utils.RouteUtils" %>
<%@ page import="ua.nure.usik.SummaryTask4.utils.ScheduleUtils" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Nazar
  Date: 04.05.2020
  Time: 00:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Route page</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/routePageStyle.css">
</head>
<body>

<jsp:include page="_header.jsp"/>

<fmt:setLocale value="${language}"/>

<jsp:include page="_search.jsp"/>


<c:set var="depStation"
       value="${DBManager.getStationById(requestScope.connection, route.departureId)}"/>
<c:set var="arrStation"
       value="${DBManager.getStationById(requestScope.connection, route.arrivalId)}"/>
<c:set var="schedule"
       value="${DBManager.getScheduleById(requestScope.connection, route.scheduleId)}"/>

<div class="route_page">

    <div class="route_info">

        <table>
            <thead>
            <fmt:bundle basename="staticInformation" prefix="table.th.">
                <th><fmt:message key="stat"/></th>
                <th><fmt:message key="arr"/></th>
                <th><fmt:message key="stop"/></th>
                <th><fmt:message key="dep"/></th>
                <th><fmt:message key="travel"/></th>
                <div class="tab_border"></div>
            </fmt:bundle>
            </thead>
            <tbody>
            <tr>
                <td class="main stat">${language == 'en' ? depStation.name : depStation.nameRu}</td>
                <td class="main time"></td>
                <td class="main time"></td>
                <td class="main time">${schedule.arrivalTime}</td>
                <td class="main travel"></td>
            </tr>
            <c:forEach items="${listInterStation}" var="route_inf">
                <tr>
                    <td class="stat">${language == 'en' ? route_inf.key.name : route_inf.key.nameRu}</td>
                    <td class="time">${route_inf.value.departureTime}</td>
                    <td class="time">${ScheduleUtils.convertIntToTime(ScheduleUtils.durationDate(route_inf.value.departureTime,
                            route_inf.value.arrivalTime))}</td>
                    <td class="time">${route_inf.value.arrivalTime}</td>
                    <td class="travel">${ScheduleUtils.convertIntToTime(route_inf.value.travelTime)}</td>
                </tr>
            </c:forEach>
            <tr>
                <fmt:parseNumber var="hour1" type="number" integerOnly="true"
                                 value="${schedule.travelTime / 60}"/>
                <fmt:parseNumber var="minutes1" type="number" integerOnly="true"
                                 value="${schedule.travelTime % 60}"/>

                <td class="main stat">${language == 'en' ? arrStation.name : arrStation.nameRu}</td>
                <td class="main time">${schedule.departureTime}</td>
                <td class="main time"></td>
                <td class="main time"></td>
                <td class="main travel">${hour1}:${minutes1}</td>
            </tr>
            </tbody>
        </table>

        <div class="choose_tick">
            <fmt:bundle basename="staticInformation" prefix="">

                <div class="numeration"><fmt:message key="numeration.1"/></div>
                <div class="tick_type">

                </div>

                <div class="numeration"><fmt:message key="numeration.2"/></div>
                <div class="car_type">

                </div>
            </fmt:bundle>
        </div>
    </div>


</div>


<jsp:include page="_footer.jsp"/>

</body>
</html>
