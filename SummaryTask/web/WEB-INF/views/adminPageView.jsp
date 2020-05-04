<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: Nazar
  Date: 21.03.2020
  Time: 19:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<html>
<head>
    <title>Add new route</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/adminPageStyle.css"/>
</head>
<style>
    select {
        background-color: rgba(31, 41, 77, 0.51);
    }

    input {
        background-color: rgba(31, 41, 77, 0.51);
    }

    .invisibility {
        background-color: rgba(31, 41, 77, 0);
        border: none;
    }

    table {
        border-collapse: collapse;
        border-spacing: 0;
    }

    th {
        background-color: rgba(31, 41, 77, 0.34);
    }

    th, td {
        width: auto;
        text-align: center;
        border-collapse: collapse;
        border-spacing: 0;
        border-right: 2px solid;
        border-left: 2px solid;
        padding-right: 10px;
        padding-left: 10px;
        border-color: rgba(31, 41, 77, 0.51);
    }

    .backgroundTable {
        background-color: rgba(31, 41, 77, 0.31);
    }

    .reg {
        border-radius: 5px;
        height: 25px;
        width: 200px;
    }

    .reg::placeholder {
        color: white;
    }

</style>
<body>
<jsp:include page="_header.jsp"/>

<fmt:setLocale value="${language}"/>
<fmt:requestEncoding value="UTF-8"/>

<div class="parallax">

    <fmt:bundle basename="staticInformation" prefix="admin.page.">
        <button class="tablink" onclick="openPage('Home', this, '#1F294D')" id="defaultOpen"><fmt:message
                key="tc"/></button>
        <button class="tablink" onclick="openPage('News', this, '#1F294D')"><fmt:message key="rs"/></button>
        <button class="tablink" onclick="openPage('Contact', this, '#1F294D')"><fmt:message key="tick"/></button>
        <button class="tablink" onclick="openPage('About', this, '#1F294D')"><fmt:message key="us"/></button>
    </fmt:bundle>

    <div id="Home" class="tabcontent">
        <fmt:bundle basename="staticInformation" prefix="admin.page.tc.">

            <p style="color: red;">${errorTC}</p>

            <p style="color: red;">${sessionScope.delTrainStatus}</p>
            <p style="color: red;">${sessionScope.delCarriageStatus}</p>
            <p style="color: red;">${sessionScope.editCarriageStatus}</p>

            <button class="accordion"><fmt:message key="all_train"/></button>
            <div class="panel">
                <fmt:bundle basename="staticInformation" prefix="admin.">
                    <table>
                        <thead>
                        <tr>

                            <th><fmt:message key="id.train_id"/></th>
                            <th><fmt:message key="page.tc.type"/></th>
                            <th></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${trains}" var="pair">
                            <tr>
                                <td>${pair.value.id}</td>
                                <td>${pair.key}</td>
                                <td>
                                    <form action="${pageContext.request.contextPath}/deleteTrain" method="post">
                                        <input type="hidden" value="${pair.value.id}" name="trainId">
                                        <fmt:message key="button.del" var="del_button"/>
                                        <input type="submit" value="${del_button}">
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </fmt:bundle>
            </div>


            <button class="accordion"><fmt:message key="all_carr"/></button>
            <c:set value="1" var="i"/>
            <div class="panel">
                <table>
                    <thead>
                    <tr>
                        <fmt:bundle basename="staticInformation" prefix="admin.">
                            <th><fmt:message key="id.train_id"/></th>
                            <th><fmt:message key="id.carriage_id"/></th>
                        </fmt:bundle>
                        <th><fmt:message key="type"/></th>
                        <th><fmt:message key="count_seats"/></th>
                        <th><fmt:message key="count_aval_seats"/></th>
                        <th><fmt:message key="rest"/></th>
                        <th></th>
                        <th></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${carriages}" var="pair">
                        <tr>
                            <td>
                                <form action="${pageContext.request.contextPath}/editCarriage" method="post"
                                      id="edCarForm${i}">
                                    <input type="hidden" value="${pair.value.value.id}" name="carriageId">
                                    <input type="number" class="invisibility" value="${pair.key}" name="trainId">
                                </form>
                            </td>
                            <td>${pair.value.value.id}</td>
                            <td>${pair.value.key}</td>
                            <td>${pair.value.value.countSeats}</td>
                            <td>${pair.value.value.countAvailableSeats}</td>
                            <td>${pair.value.value.haveRestaurant}</td>
                            <fmt:bundle basename="staticInformation" prefix="admin.">
                                <td>
                                    <fmt:message key="button.edit" var="edit_button"/>
                                    <input type="submit" value="${edit_button}"
                                           form="edCarForm${i}">
                                </td>
                                <td>
                                    <form action="${pageContext.request.contextPath}/deleteCarriage" method="post">
                                        <input type="hidden" value="${pair.value.value.id}" name="carriageId">
                                        <input type="submit" value="${del_button}">
                                    </form>
                                </td>
                            </fmt:bundle>
                        </tr>
                        <c:set var="i" value="${i + 1}"/>
                    </c:forEach>
                    </tbody>
                </table>
            </div>

            <h3><fmt:message key="add_tr"/></h3>
            <p style="color: red;">${sessionScope.addTrainStatus}</p>
            <br/>

            <form action="${pageContext.request.contextPath}/addTrain" method="post">
                <label>
                    <fmt:message key="select_train"/>
                    <select name="trainType" required>
                        <fmt:bundle basename="enum" prefix="type.train.">
                            <option value="STREAM"><fmt:message key="stream"/></option>
                            <option value="ELECTRIC"><fmt:message key="electr"/></option>
                            <option value="DIESEL"><fmt:message key="disel"/></option>
                        </fmt:bundle>
                    </select>
                </label>
                <input type="submit" value="<fmt:message key="add_tr"/>"/>
            </form>

            <br/>
            <h3><fmt:message key="all_carr"/></h3>
            <p style="color: red;">${sessionScope.addCarriageStatus}</p>
            <br/>

            <form action="${pageContext.request.contextPath}/addCarriage" method="post">
                <label>
                    <fmt:bundle basename="staticInformation" prefix="admin.">
                        <fmt:message key="id.train_id"/>:
                    </fmt:bundle>
                    <input type="number" name="trainId" required>
                </label>
                <br/>
                <label>
                    <fmt:message key="select_carr"/>
                    <select name="type" required>
                        <fmt:bundle basename="enum" prefix="type.carriage.">
                            <option value="COUPE"><fmt:message key="coupe"/></option>
                            <option value="LUX"><fmt:message key="lux"/></option>
                            <option value="PLATSKART"><fmt:message key="plats"/></option>
                            <option value="SEAT_PLACE"><fmt:message key="seat"/></option>
                        </fmt:bundle>
                    </select>
                </label>
                <br/>
                <label>
                    <fmt:message key="amount_seats"/>:
                    <input type="number" name="countSeats" required/>
                </label>
                <br/>
                <label>
                    <fmt:message key="rest"/>:
                    <input type="checkbox" name="rest" value="Y">
                </label>
                <br/>
                <input type="submit" value="<fmt:message key="button.add_carr"/>"/>
            </form>

        </fmt:bundle>
    </div>

    <div id="News" class="tabcontent">
        <fmt:bundle basename="staticInformation" prefix="admin.page.rs.">

            <p style="color: red;">${errorSR}</p>

            <p style="color: red;">${sessionScope.delStationStatus}</p>
            <p style="color: red;">${sessionScope.editStationStatus}</p>

            <p style="color: red;">${sessionScope.delRouteStatus}</p>
            <p style="color: red;">${sessionScope.editRouteStatus}</p>

            <p style="color: red;">${sessionScope.delIntStatus}</p>
            <p style="color: red;">${sessionScope.editIntStatus}</p>

            <button class="accordion"><fmt:message key="all_stat"/></button>
            <div class="panel">
                <table>
                    <thead>
                    <tr>
                        <fmt:bundle basename="staticInformation" prefix="admin.id.">
                            <th><fmt:message key="station_id"/></th>
                        </fmt:bundle>
                        <th><fmt:message key="name"/></th>
                        <th></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${stationList}" var="station">
                        <tr>
                            <td>${station.id}</td>
                            <td>
                                <form action="${pageContext.request.contextPath}/editStation" method="post"
                                      id="edStForm">
                                    <input type="hidden" value="${station.id}" name="stationId">
                                    <c:if test="${language == 'en'}">
                                        <input type="text" class="invisibility" name="stationName"
                                               value="${station.name}"
                                               required>
                                    </c:if>
                                    <c:if test="${language == 'ru'}">
                                        <input type="text" class="invisibility" name="stationName"
                                               value="${station.nameRu}"
                                               required>
                                    </c:if>
                                    <input type="submit" value="${edit_button}">
                                </form>
                            </td>
                            <td>
                                <form action="${pageContext.request.contextPath}/deleteStation" method="post">
                                    <input type="hidden" value="${station.id}" name="stationId">
                                    <input type="submit" value="${del_button}">
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>


            <button class="accordion"><fmt:message key="all_route"/></button>
            <div class="panel">
                <table>
                    <thead>
                    <tr>
                        <fmt:bundle basename="staticInformation" prefix="admin.id.">
                            <th><fmt:message key="route_id"/></th>
                            <th><fmt:message key="train_id"/></th>
                        </fmt:bundle>
                        <th><fmt:message key="dep_s"/></th>
                        <th><fmt:message key="arr_s"/></th>
                        <th><fmt:message key="dep_t"/></th>
                        <th><fmt:message key="arr_t"/></th>
                        <th><fmt:message key="travel_t"/></th>
                        <th></th>
                        <th></th>
                    </tr>
                    </thead>
                    <tbody>


                    <c:forEach begin="1" end="${routeInfoMap.size()}" step="1" var="i">

                        <tr>
                            <c:forEach items="${routeInfoMap}" var="entry">

                                <c:choose>

                                    <c:when test="${entry.key == 'Route'.concat(i)}">
                                        <td>${entry.value.id}</td>
                                        <td>
                                            <form action="${pageContext.request.contextPath}/deleteRoute"
                                                  method="post" id="delRoute${i}">
                                                <input type="hidden" value="${entry.value.id}" name="route_id">
                                            </form>

                                            <form action="${pageContext.request.contextPath}/editRoute"
                                                  method="post" id="edRoute${i}">
                                                <input type="hidden" value="${entry.value.id}" name="route_id">
                                                <input type="number" class="invisibility" value="${entry.value.trainId}"
                                                       name="train_id" required>

                                            </form>
                                        </td>
                                    </c:when>

                                    <c:when test="${entry.key == 'Station_dep'.concat(i)}">
                                        <c:if test="${language == 'en'}">
                                            <td>
                                                <input type="text" value="${entry.value.name}" name="station_dep"
                                                       form="edRoute${i}" required class="invisibility">
                                            </td>
                                        </c:if>
                                        <c:if test="${language == 'ru'}">
                                            <td>
                                                <input type="text" value="${entry.value.nameRu}" name="station_dep"
                                                       form="edRoute${i}" required class="invisibility">
                                            </td>
                                        </c:if>
                                    </c:when>

                                    <c:when test="${entry.key == 'Station_arr'.concat(i)}">
                                        <c:if test="${language == 'en'}">
                                            <td>
                                                <input type="text" value="${entry.value.name}" name="station_arr"
                                                       form="edRoute${i}" required class="invisibility">
                                            </td>
                                        </c:if>
                                        <c:if test="${language == 'ru'}">
                                            <td>
                                                <input type="text" value="${entry.value.nameRu}" name="station_arr"
                                                       form="edRoute${i}" required class="invisibility">
                                            </td>
                                        </c:if>
                                    </c:when>

                                    <c:when test="${entry.key == 'Schedule'.concat(i)}">
                                        <td>
                                            <input type="text" value="${entry.value.departureTime}" name="dep_time"
                                                   form="edRoute${i}" required class="invisibility">
                                        </td>
                                        <td>
                                            <input type="text" value="${entry.value.arrivalTime}" name="arr_time"
                                                   form="edRoute${i}" required class="invisibility">
                                        </td>
                                        <fmt:parseNumber var="hour1" type="number" integerOnly="true"
                                                         value="${entry.value.travelTime / 60}"/>
                                        <fmt:parseNumber var="minutes1" type="number" integerOnly="true"
                                                         value="${entry.value.travelTime % 60}"/>
                                        <td>${hour1}:${minutes1}</td>
                                        <td>
                                            <input type="submit" value="${edit_button}" form="edRoute${i}">
                                        </td>

                                        <td>
                                            <input type="submit" value="${del_button}" form="delRoute${i}">
                                        </td>

                                    </c:when>

                                </c:choose>


                            </c:forEach>
                        </tr>

                    </c:forEach>

                    </tbody>
                </table>
            </div>

            <button class="accordion"><fmt:message key="all_intm"/></button>
            <div class="panel">
                <table>
                    <thead>
                    <tr>
                        <fmt:bundle basename="staticInformation" prefix="admin.id.">
                            <th><fmt:message key="route_id"/></th>
                        </fmt:bundle>
                        <th><fmt:message key="stat"/></th>
                        <th><fmt:message key="dep_t"/></th>
                        <th><fmt:message key="arr_t"/></th>
                        <th><fmt:message key="travel_t"/><br><fmt:message key="in_minutes"/></th>
                        <th></th>
                        <th></th>
                    </tr>
                    </thead>
                    <tbody>

                    <c:forEach begin="1" end="${interStationMap.size()}" step="1" var="i">
                        <tr>
                            <c:forEach items="${interStationMap}" var="entry">

                                <c:choose>

                                    <c:when test="${entry.key == 'IntermediateStation'.concat(i)}">
                                        <td>${entry.value.routeId}
                                            <form action="${pageContext.request.contextPath}/deleteIntermediateStation"
                                                  method="post" id="delInSt${i}">
                                                <input type="hidden" value="${entry.value.routeId}" name="route_id">
                                                <input type="hidden" value="${entry.value.stationId}" name="station_id">
                                            </form>

                                            <form action="${pageContext.request.contextPath}/editIntermediateStation"
                                                  method="post" id="edInSt${i}">
                                                <input type="hidden" value="${entry.value.routeId}" name="route_id">
                                            </form>

                                        </td>
                                    </c:when>

                                    <c:when test="${entry.key == 'Station'.concat(i)}">
                                        <td>
                                            <input type="hidden" value="${entry.value.id}" name="station_id"
                                                   form="edInSt${i}">
                                            <c:if test="${language == 'en'}">
                                                <input type="text" class="invisibility" value="${entry.value.name}"
                                                       name="station_name" required form="edInSt${i}">
                                            </c:if>
                                            <c:if test="${language == 'ru'}">
                                                <input type="text" class="invisibility" value="${entry.value.nameRu}"
                                                       name="station_name" required form="edInSt${i}">
                                            </c:if>
                                        </td>
                                    </c:when>

                                    <c:when test="${entry.key == 'Schedule'.concat(i)}">
                                        <td>
                                            <input type="text" value="${entry.value.departureTime}" name="dep_time"
                                                   form="edInSt${i}" required class="invisibility">
                                        </td>
                                        <td>
                                            <input type="text" value="${entry.value.arrivalTime}" name="arr_time"
                                                   form="edInSt${i}" required class="invisibility">
                                        </td>

                                        <td>
                                            <input type="number" value="${entry.value.travelTime}" name="trv_time"
                                                   form="edInSt${i}" required class="invisibility">
                                        </td>

                                        <td>
                                            <input type="submit" value="${edit_button}" form="edInSt${i}">
                                        </td>

                                        <td>
                                            <input type="submit" value="${del_button}" form="delInSt${i}">
                                        </td>

                                    </c:when>

                                </c:choose>

                            </c:forEach>
                        </tr>

                    </c:forEach>

                    </tbody>
                </table>
            </div>

            <h3><fmt:message key="add_stat"/></h3>
            <p style="color: red;">${sessionScope.addStationStatus}</p>
            <br/>

            <form action="${pageContext.request.contextPath}/addStation" method="post">
                <label>
                    <fmt:message key="stat_name"/>
                    <input type="text" name="stationName" required/>
                </label>
                <input type="submit" value="<fmt:message key="add_stat"/>"/>
            </form>

            <br/>
            <h3><fmt:message key="add_route"/></h3>
            <p style="color: red;">${sessionScope.addRouteStatus}</p>
            <br/>

            <form action="${pageContext.request.contextPath}/addRoute" method="post">
                <label>
                    <fmt:message key="tick_price"/>
                    <input type="number" name="price" required>
                </label>
                <br/>
                <label>
                    <fmt:bundle basename="staticInformation" prefix="admin.id.">
                        <fmt:message key="train_id"/>:
                    </fmt:bundle>
                    <input type="number" name="trainId" required>
                </label>
                <br/>
                <label>
                    <fmt:message key="dep_s"/>:
                    <input type="text" name="depStation" required>
                </label>
                <br/>
                <label>
                    <fmt:message key="arr_s"/>:
                    <input type="text" name="arrStation" required>
                </label>
                <br/>
                <label>
                    <fmt:message key="dep_t"/>:
                    <input type="datetime-local" name="depTime" required>
                </label>
                <br/>
                <label>
                    <fmt:message key="arr_t"/>:
                    <input type="datetime-local" name="arrTime" required>
                </label>
                <br/>
                <input type="submit" value="<fmt:message key="add_route"/>"/>
            </form>

            <br/>
            <h3><fmt:message key="add_intm"/></h3>
            <p style="color: red;">${sessionScope.addInterStationStatus}</p>
            <br/>

            <form action="${pageContext.request.contextPath}/addIntermediateStation" method="post">
                <label>
                    <fmt:bundle basename="staticInformation" prefix="admin.id.">
                        <fmt:message key="route_id"/>:
                    </fmt:bundle>
                    <input type="number" name="routeId" required>
                </label>
                <br/>
                <label>
                    <fmt:message key="stat_name"/>
                    <input type="text" name="stationName" required/>
                </label>
                <br/>
                <label>
                    <fmt:message key="dep_t"/>:
                    <input type="datetime-local" name="depTime" required>
                </label>
                <br/>
                <label>
                    <fmt:message key="arr_t"/>:
                    <input type="datetime-local" name="arrTime" required>
                </label>
                <br/>
                <label>
                    <fmt:message key="enter_travel_minutes"/>
                    <input type="number" name="travelTime" required>
                </label>
                <br/>
                <input type="submit" value="<fmt:message key="button.add_intms"/>"/>
            </form>

        </fmt:bundle>
    </div>

    <div id="Contact" class="tabcontent">
        <fmt:bundle basename="staticInformation" prefix="admin.page.tick.">
            <p style="color: red;">${errorTK}</p>
            <p style="color: red;">${sessionScope.editTicketsStatus}</p>

            <table class="backgroundTable">
                <thead>
                <tr>
                    <fmt:bundle basename="staticInformation" prefix="admin.id.">
                        <th><fmt:message key="route_id"/></th>
                    </fmt:bundle>
                    <th><fmt:message key="price"/></th>
                    <th><fmt:message key="amount"/></th>
                    <th><fmt:message key="amount_aval"/></th>
                    <th></th>
                </tr>
                </thead>

                <tbody>
                <c:set value="1" var="i"/>
                <c:forEach items="${ticketInfoMap}" var="entry">
                    <tr>

                        <td>${entry.key.key}
                            <form action="${pageContext.request.contextPath}/editTickets"
                                  method="post" id="edTick${i}">
                                <input type="hidden" value="${entry.key.key}" name="route_id">
                            </form>
                        </td>

                        <td>
                            <input type="number" step="0.1" value="${entry.key.value}" name="price"
                                   form="edTick${i}" required class="invisibility">
                        </td>

                        <td>${entry.value.key}</td>
                        <td>${entry.value.value}</td>

                        <td>
                            <input type="submit" value="${edit_button}" form="edTick${i}">
                        </td>

                    </tr>
                    <c:set value="${i + 1}" var="i"/>
                </c:forEach>
                </tbody>
            </table>

        </fmt:bundle>
    </div>

    <div id="About" class="tabcontent">
        <fmt:bundle basename="staticInformation" prefix="admin.page.us.">

            <p style="color: red;">${errorUs}</p>
            <p style="color: red;">${sessionScope.addAdminStatus}</p>
            <h3 style="padding-left: 50px; margin-bottom: 5px"><fmt:message key="add_admin"/></h3>

            <form action="${pageContext.request.contextPath}/addAdmin" method="post">
                <input type="text" name="f_name" placeholder="<fmt:message key="en_f_name"/>" required class="reg">
                <br/>
                <input type="text" name="l_name" placeholder="<fmt:message key="en_l_name"/>" required class="reg">
                <br/>
                <input type="text" name="login" placeholder="<fmt:message key="en_login"/>" required class="reg">
                <br/>
                <input type="password" name="pass" placeholder="<fmt:message key="en_pass"/>" required class="reg">
                <br/>
                <input type="submit" value="<fmt:message key="add_admin"/>" class="reg">
            </form>

            <br/>
            <br/>

            <table class="backgroundTable">
                <thead>
                <tr>
                    <th><fmt:message key="f_name"/></th>
                    <th><fmt:message key="l_name"/></th>
                    <th><fmt:message key="email"/></th>
                    <th><fmt:message key="role"/></th>
                </tr>
                </thead>

                <tbody>

                <c:forEach items="${usersInfoList}" var="entry">
                    <tr>
                        <td>${entry.key.firstName}</td>
                        <td>${entry.key.lastName}</td>
                        <td>${entry.key.email}</td>
                        <td>${entry.value}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>


        </fmt:bundle>

        ${sessionScope.delTrainStatus= null}
        ${sessionScope.delCarriageStatus= null}
        ${sessionScope.editCarriageStatus= null}
        ${sessionScope.addTrainStatus= null}
        ${sessionScope.addCarriageStatus= null}
        ${sessionScope.delStationStatus= null}
        ${sessionScope.editStationStatus= null}
        ${sessionScope.delRouteStatus= null}
        ${sessionScope.editRouteStatus= null}
        ${sessionScope.delIntStatus= null}
        ${sessionScope.editIntStatus= null}
        ${sessionScope.addStationStatus= null}
        ${sessionScope.addRouteStatus= null}
        ${sessionScope.addInterStationStatus= null}
        ${sessionScope.editTicketsStatus= null}
        ${sessionScope.addAdminStatus= null}
    </div>


    <script>
        var acc = document.getElementsByClassName("accordion");
        var i;

        for (i = 0; i < acc.length; i++) {
            acc[i].addEventListener("click", function () {
                this.classList.toggle("active");
                var panel = this.nextElementSibling;
                if (panel.style.display === "block") {
                    panel.style.display = "none";
                } else {
                    panel.style.display = "block";
                }
            });
        }

    </script>

    <script>
        function openPage(pageName, elmnt, color) {
            var i, tabcontent, tablinks;
            tabcontent = document.getElementsByClassName("tabcontent");
            for (i = 0; i < tabcontent.length; i++) {
                tabcontent[i].style.display = "none";
            }
            tablinks = document.getElementsByClassName("tablink");
            for (i = 0; i < tablinks.length; i++) {
                tablinks[i].style.backgroundColor = color;
                tablinks[i].style.opacity = "0.30";

            }
            document.getElementById(pageName).style.display = "block";
            elmnt.style.backgroundColor = color;
            elmnt.style.opacity = "0.36";
        }

        // Get the element with id="defaultOpen" and click on it
        document.getElementById("defaultOpen").click();
    </script>

</div>

<jsp:include page="_footer.jsp"/>

</body>
</html>
