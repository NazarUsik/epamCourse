<%@ page import="ua.nure.usik.SummaryTask4.db.DBManager" %>
<%@ page import="ua.nure.usik.SummaryTask4.utils.RouteUtils" %>
<%--
  Created by IntelliJ IDEA.
  User: Nazar
  Date: 22.03.2020
  Time: 20:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Product List</title>
    <script src="https://kit.fontawesome.com/a076d05399.js"></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/routeListStyle.css">
</head>
<body>

<jsp:include page="_header.jsp"/>

<fmt:setLocale value="${language}"/>

<jsp:include page="_search.jsp"/>

<br/>
<p style="color: red;">${errorString}</p>

<div class="route_table">
    <div class="tab_border">
        <div class="tab_body">
            <c:forEach var="i" step="1" begin="1" end="${routeMap.size()/4}">
                <div class="tab_body_row">
                    <fmt:bundle basename="staticInformation" prefix="list.">
                        <c:forEach items="${routeMap}" var="pair">

                            <c:choose>

                                <c:when test="${pair.key == 'Schedule'.concat(i)}">
                                    <div class="time">
                                        <div class="tab_body_column dep_time">${pair.value.departureTime}</div>
                                        <div class="tab_body_column arr_time">${pair.value.arrivalTime}</div>
                                    </div>

                                    <fmt:parseNumber var="hour1" type="number" integerOnly="true"
                                                     value="${pair.value.travelTime / 60}"/>
                                    <fmt:parseNumber var="minutes1" type="number" integerOnly="true"
                                                     value="${pair.value.travelTime % 60}"/>
                                </c:when>

                                <c:when test="${pair.key == 'Station_dep'.concat(i)}">
                                    <jsp:useBean id="depStation" class="ua.nure.usik.SummaryTask4.db.entity.Station">
                                        <jsp:setProperty name="depStation" property="id" value="${pair.value.id}"/>
                                        <jsp:setProperty name="depStation" property="name" value="${pair.value.name}"/>
                                        <jsp:setProperty name="depStation" property="nameRu"
                                                         value="${pair.value.nameRu}"/>
                                    </jsp:useBean>
                                    <c:set var="dep_stat"
                                           value="${language == 'en' ? pair.value.name : pair.value.nameRu}"/>
                                </c:when>
                                <c:when test="${pair.key == 'Station_arr'.concat(i)}">
                                    <jsp:useBean id="arrStation" class="ua.nure.usik.SummaryTask4.db.entity.Station">
                                        <jsp:setProperty name="arrStation" property="id" value="${pair.value.id}"/>
                                        <jsp:setProperty name="arrStation" property="name" value="${pair.value.name}"/>
                                        <jsp:setProperty name="arrStation" property="nameRu"
                                                         value="${pair.value.nameRu}"/>
                                    </jsp:useBean>
                                    <div class="station">
                                        <div class="tab_body_column dep_station">${dep_stat}</div>
                                        <div class="tab_body_column arr_station">${language == 'en' ?
                                                pair.value.name : pair.value.nameRu}</div>
                                    </div>
                                </c:when>

                                <c:when test="${pair.key == 'Route'.concat(i)}">
                                    <div class="border_line"></div>
                                    <c:set value="${pair.value.id}" var="route_id"/>
                                    <div class="info">
                                        <div class="tab_body_column travel_time"><i
                                                class="far fa-clock icon"></i>
                                            <div class="info_text"><fmt:message
                                                    key="trav_time"/></div>
                                                ${hour1}:${minutes1}</div>
                                        <div class="tab_body_column train_id"><i
                                                class="fas fa-table icon"></i>
                                            <div class="info_text"><fmt:message
                                                    key="train_id"/></div>
                                                ${pair.value.trainId}</div>
                                        <jsp:useBean id="train" class="ua.nure.usik.SummaryTask4.db.entity.Train"
                                                     scope="session"/>
                                        <jsp:setProperty name="train" property="id" value="${pair.value.trainId}"/>
                                        <jsp:setProperty name="train" property="typeId"
                                                         value="${DBManager.findTrain(requestScope.connection,
                                                          train.id).typeId}"/>
                                        <div class="tab_body_column train_type"><i
                                                class="fas fa-subway icon"></i>
                                            <div class="info_text"><fmt:message
                                                    key="train_type"/></div>
                                            <fmt:bundle basename="enum" prefix="type.train.">
                                                <c:choose>
                                                    <c:when test="${train.typeId == 1}">
                                                        <fmt:message key="electr"/>
                                                    </c:when>
                                                    <c:when test="${train.typeId == 2}">
                                                        <fmt:message key="stream"/>
                                                    </c:when>
                                                    <c:when test="${train.typeId == 3}">
                                                        <fmt:message key="disel"/>
                                                    </c:when>
                                                </c:choose>
                                            </fmt:bundle>
                                        </div>
                                    </div>
                                </c:when>
                            </c:choose>
                        </c:forEach>

                        <div class="border_line"></div>
                        <div class="tick_inf">
                            <c:set var="tick_inf"
                                   value="${DBManager.getTicketsInfoByRoute(requestScope.connection, route_id)}"/>
                            <div class="count_tick">
                                <div style="color: #646464; display: initial;"><i
                                        class="fas fa-ticket-alt icon"></i> <fmt:message key="tick.count"/></div>
                                    ${tick_inf.key}</div>
                            <div class="price">
                                <div style="color: #646464; display: initial;"><i
                                        class="fas fa-money-check-alt icon"></i> <fmt:message key="tick.price"/></div>
                                    ${RouteUtils.calculateTicketPrice(requestScope.connection, route_id, depStation, arrStation)}
                            </div>
                        </div>

                        <div class="buy_tick_btn">
                            <form action="${pageContext.request.contextPath}/routePage" method="get">
                                <input type="hidden" name="route_id"
                                       value="${route_id}">
                                <input type="submit" value="<fmt:message key="btn"/>">
                            </form>
                        </div>
                    </fmt:bundle>
                </div>
            </c:forEach>
        </div>
    </div>
</div>


<jsp:include page="_footer.jsp"/>
</body>
</html>