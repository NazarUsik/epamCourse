<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: Nazar
  Date: 21.03.2020
  Time: 19:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add new route</title>
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

<div class="parallax">

    <button class="tablink" onclick="openPage('Home', this, '#1F294D')" id="defaultOpen">Add train\carriage</button>
    <button class="tablink" onclick="openPage('News', this, '#1F294D')">Add route\station</button>
    <button class="tablink" onclick="openPage('Contact', this, '#1F294D')">Tickets</button>
    <button class="tablink" onclick="openPage('About', this, '#1F294D')">Users</button>

    <div id="Home" class="tabcontent">
        <p style="color: red;">${errorTC}</p>

        <p style="color: red;">${param.delTrainStatus}</p>
        <p style="color: red;">${param.delCarriageStatus}</p>
        <p style="color: red;">${param.editCarriageStatus}</p>

        <button class="accordion">View all train</button>
        <div class="panel">
            <table>
                <thead>
                <tr>
                    <th>Train id</th>
                    <th>Type</th>
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
                                <input type="submit" value="Delete">
                            </form>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>


        <button class="accordion">View all carriage</button>
        <c:set value="1" var="i"/>
        <div class="panel">
            <table>
                <thead>
                <tr>
                    <th>Train id</th>
                    <th>Carriage id</th>
                    <th>Type</th>
                    <th>Count Seats</th>
                    <th>Count Available Seats</th>
                    <th>Restaurant</th>
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
                        <td>
                            <input type="submit" value="Edit" form="edCarForm${i}">
                        </td>
                        <td>
                            <form action="${pageContext.request.contextPath}/deleteCarriage" method="post">
                                <input type="hidden" value="${pair.value.value.id}" name="carriageId">
                                <input type="submit" value="Delete">
                            </form>
                        </td>
                    </tr>
                    <c:set var="i" value="${i + 1}"/>
                </c:forEach>
                </tbody>
            </table>
        </div>

        <h3>Add train</h3>
        <p style="color: red;">${param.addTrainStatus}</p>
        <br/>

        <form action="${pageContext.request.contextPath}/addTrain" method="post">
            <label>
                Select train type:
                <select name="trainType" required>
                    <option value="STREAM"> STREAM</option>
                    <option value="ELECTRIC"> ELECTRIC</option>
                    <option value="DIESEL"> DIESEL</option>
                </select>
            </label>
            <input type="submit" value="Add train"/>
        </form>

        <br/>
        <h3>Add carriage for train</h3>
        <p style="color: red;">${param.addCarriageStatus}</p>
        <br/>

        <form action="${pageContext.request.contextPath}/addCarriage" method="post">
            <label>
                Train id:
                <input type="number" name="trainId" required>
            </label>
            <br/>
            <label>
                Select type carriage:
                <select name="type" required>
                    <option value="COUPE"> Coupe</option>
                    <option value="LUX"> Lux</option>
                    <option value="PLATSKART"> Platskart</option>
                    <option value="SEAT_PLACE"> Seat place</option>
                </select>
            </label>
            <br/>
            <label>
                Amount seats:
                <input type="number" name="countSeats" required/>
            </label>
            <br/>
            <label>
                Restaurant:
                <input type="checkbox" name="rest" value="Y">
            </label>
            <br/>
            <input type="submit" value="Add carriage"/>
        </form>

    </div>

    <div id="News" class="tabcontent">
        <p style="color: red;">${errorSR}</p>

        <p style="color: red;">${param.delStationStatus}</p>
        <p style="color: red;">${param.editStationStatus}</p>

        <p style="color: red;">${param.delRouteStatus}</p>
        <p style="color: red;">${param.editRouteStatus}</p>

        <p style="color: red;">${param.delIntStatus}</p>
        <p style="color: red;">${param.editIntStatus}</p>

        <button class="accordion">View all station</button>
        <div class="panel">
            <table>
                <thead>
                <tr>
                    <th>Station id</th>
                    <th>Name</th>
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
                                <input type="text" class="invisibility" name="stationName" value="${station.name}"
                                       required>
                                <input type="submit" value="Edit">
                            </form>
                        </td>
                        <td>
                            <form action="${pageContext.request.contextPath}/deleteStation" method="post">
                                <input type="hidden" value="${station.id}" name="stationId">
                                <input type="submit" value="Delete">
                            </form>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>


        <button class="accordion">View all route</button>
        <div class="panel">
            <table>
                <thead>
                <tr>
                    <th>Route id</th>
                    <th>Train id</th>
                    <th>Departure station</th>
                    <th>Arrival station</th>
                    <th>Departure time</th>
                    <th>Arrival time</th>
                    <th>Travel time</th>
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
                                    <td>
                                        <input type="text" value="${entry.value.name}" name="station_dep"
                                               form="edRoute${i}" required class="invisibility">
                                    </td>
                                </c:when>

                                <c:when test="${entry.key == 'Station_arr'.concat(i)}">
                                    <td>
                                        <input type="text" value="${entry.value.name}" name="station_arr"
                                               form="edRoute${i}" required class="invisibility">
                                    </td>
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
                                        <input type="submit" value="Edit" form="edRoute${i}">
                                    </td>

                                    <td>
                                        <input type="submit" value="Delete" form="delRoute${i}">
                                    </td>

                                </c:when>

                            </c:choose>


                        </c:forEach>
                    </tr>

                </c:forEach>

                </tbody>
            </table>
        </div>

        <button class="accordion">View all intermediate station</button>
        <div class="panel">
            <table>
                <thead>
                <tr>
                    <th>Route id</th>
                    <th>Station</th>
                    <th>Departure time</th>
                    <th>Arrival time</th>
                    <th>Travel time<br>(in minutes)</th>
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
                                        <input type="text" class="invisibility" value="${entry.value.name}"
                                               name="station_name" required form="edInSt${i}">
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
                                        <input type="submit" value="Edit" form="edInSt${i}">
                                    </td>

                                    <td>
                                        <input type="submit" value="Delete" form="delInSt${i}">
                                    </td>

                                </c:when>

                            </c:choose>

                        </c:forEach>
                    </tr>

                </c:forEach>

                </tbody>
            </table>
        </div>

        <h3>Add station</h3>
        <p style="color: red;">${param.addStationStatus}</p>
        <br/>

        <form action="${pageContext.request.contextPath}/addStation" method="post">
            <label>
                Enter station name:
                <input type="text" name="stationName" required/>
            </label>
            <input type="submit" value="Add station"/>
        </form>

        <br/>
        <h3>Add route</h3>
        <p style="color: red;">${param.addRouteStatus}</p>
        <br/>

        <form action="${pageContext.request.contextPath}/addRoute" method="post">
            <label>
                Ticket price:
                <input type="number" name="price" required>
            </label>
            <br/>
            <label>
                Train id:
                <input type="number" name="trainId" required>
            </label>
            <br/>
            <label>
                Departure station:
                <input type="text" name="depStation" required>
            </label>
            <br/>
            <label>
                Arrival station:
                <input type="text" name="arrStation" required>
            </label>
            <br/>
            <label>
                Departure time:
                <input type="datetime-local" name="depTime" required>
            </label>
            <br/>
            <label>
                Arrival time:
                <input type="datetime-local" name="arrTime" required>
            </label>
            <br/>
            <input type="submit" value="Add route"/>
        </form>

        <br/>
        <h3>Add intermediate station for route</h3>
        <p style="color: red;">${param.addInterStationStatus}</p>
        <br/>

        <form action="${pageContext.request.contextPath}/addIntermediateStation" method="post">
            <label>
                Route id:
                <input type="number" name="routeId" required>
            </label>
            <br/>
            <label>
                Enter station name:
                <input type="text" name="stationName" required/>
            </label>
            <br/>
            <label>
                Departure time:
                <input type="datetime-local" name="depTime" required>
            </label>
            <br/>
            <label>
                Arrival time:
                <input type="datetime-local" name="arrTime" required>
            </label>
            <br/>
            <label>
                Travel time from previous station to this (in minutes):
                <input type="number" name="travelTime" required>
            </label>
            <br/>
            <input type="submit" value="Add intermediate station"/>
        </form>


    </div>

    <div id="Contact" class="tabcontent">
        <p style="color: red;">${errorTK}</p>
        <p style="color: red;">${param.editTicketsStatus}</p>

        <table class="backgroundTable">
            <thead>
            <tr>
                <th>Route id</th>
                <th>Price</th>
                <th>Amount</th>
                <th>Amount available</th>
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
                        <input type="submit" value="Edit" form="edTick${i}">
                    </td>

                </tr>
                <c:set value="${i + 1}" var="i"/>
            </c:forEach>
            </tbody>
        </table>


    </div>

    <div id="About" class="tabcontent">

        <p style="color: red;">${errorUs}</p>
        <p style="color: red;">${param.addAdminStatus}</p>
        <h3 style="padding-left: 50px; margin-bottom: 5px">Add Admin</h3>

        <form action="${pageContext.request.contextPath}/addAdmin" method="post">
            <input type="text" name="f_name" placeholder="Enter first name" required class="reg">
            <br/>
            <input type="text" name="l_name" placeholder="Enter last name" required class="reg">
            <br/>
            <input type="text" name="login" placeholder="Enter login" required class="reg">
            <br/>
            <input type="password" name="pass" placeholder="Enter password" required class="reg">
            <br/>
            <input type="submit" value="Add admin" class="reg">
        </form>

        <br/>
        <br/>

        <table class="backgroundTable">
            <thead>
            <tr>
                <th>First name</th>
                <th>Last name</th>
                <th>Email</th>
                <th>Role</th>
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
