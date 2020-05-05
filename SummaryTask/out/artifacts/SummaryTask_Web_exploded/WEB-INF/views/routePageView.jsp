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
                <form id="buy_form" action="${pageContext.request.contextPath}/buyTicket" method="post">
                    <div class="numeration"><fmt:message key="numeration"/></div>

                    <div class="car_type">
                        <c:set var="price" value="${RouteUtils.calculateTicketPrice(requestScope.connection,
                                        route.id,sessionScope.dep_station, sessionScope.arr_station)}"/>
                        <input type="hidden" name="price" value="${price}">
                        <c:forEach var="tr_inf" items="${trainInfo}">
                            <c:choose>
                                <c:when test="${tr_inf.key=='lux' || tr_inf.key=='Люкс'}">
                                    <c:set var="price" value=" ${price = price * 3}"/>
                                </c:when>
                                <c:when test="${tr_inf.key=='coupe' || tr_inf.key=='Купе'}">
                                    <c:set var="price" value="${price = price * 1.5}"/>
                                </c:when>
                            </c:choose>

                            <label class="contain radio_type">${tr_inf.key}
                                <input type="radio" name="carr_type" checked value="${tr_inf.key}" required>
                                <span class="checkmark"></span>
                                <p class="text"><fmt:message key="amount_seats"/></p>
                                <p class="seat_price"> ${tr_inf.value}</p>
                                <p class="text"><fmt:message key="price"/></p>
                                <p class="seat_price"> ${price}</p>
                            </label>
                        </c:forEach>
                    </div>

                    <input type="hidden" name="routeId" value="${route.id}">
                    <c:if test="${sessionScope.loginedUser != null}">
                        <span id="opBtn" onclick="openBuyForm()"><fmt:message
                                key="buy"/></span>
                    </c:if>
                    <c:if test="${sessionScope.loginedUser == null}">
                        <span id="opBtn" onclick="openForm()"><fmt:message
                                key="buy"/></span>
                    </c:if>
                </form>
            </fmt:bundle>
        </div>
    </div>


</div>

<jsp:include page="_tourPaymentView.jsp"/>
<jsp:include page="_footer.jsp"/>

<script>

    function openBuyForm() {
        document.getElementById('id02').style.display = 'block';
    }


</script>

</body>
</html>
