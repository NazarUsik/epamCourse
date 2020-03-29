<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/addRouteStyle.css"/>
</head>
<style>
    select input{
        background-color: rgba(31, 41, 77, 0.51);
    }
</style>
<body>
<jsp:include page="_header.jsp"/>

<button class="tablink" onclick="openPage('Home', this, '#1F294D')">Add train\carriage</button>
<button class="tablink" onclick="openPage('News', this, '#1F294D')" id="defaultOpen"></button>
<button class="tablink" onclick="openPage('Contact', this, '#1F294D')">Contact</button>
<button class="tablink" onclick="openPage('About', this, '#1F294D')">About</button>

<div id="Home" class="tabcontent">
    <p style="color: red;">${error}</p>

    <button class="accordion">View all train</button>
    <div class="panel">
        <table>
            <thead>
            <tr>
                <th>Train id</th>
                <th>Type</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${trains}" var="pair">
                <tr>
                    <td>${pair.value.id}</td>
                    <td>${pair.key}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

    <button class="accordion">View all carriage</button>
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
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${carriages}" var="pair">
                <tr>
                    <td>${pair.key}</td>
                    <td>${pair.value.value.id}</td>
                    <td>${pair.value.key}</td>
                    <td>${pair.value.value.countSeats}</td>
                    <td>${pair.value.value.countAvailableSeats}</td>
                    <td>${pair.value.value.haveRestaurant}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

    <h3>Add train</h3>
    <p style="color: red;">${addTrain}</p>
    <br/>

    <form action="${pageContext.request.contextPath}/addRoute" method="post">
        <input type="hidden" name="form" value="1"/>
        <label>
            Select train type:
            <select name="trainType">
                <option value="STREAM"> STREAM</option>
                <option value="ELECTRIC"> ELECTRIC</option>
                <option value="DIESEL"> DIESEL</option>
            </select>
        </label>
        <input type="submit" value="Add train"/>
    </form>

    <br/>
    <h3>Add carriage for train</h3>
    <p style="color: red;">${addCarriage}</p>
    <br/>

    <form action="${pageContext.request.contextPath}/addRoute" method="post">
        <input type="hidden" name="form" value="2"/>
        <label>
            Train id:
            <input type="number" name="trainId">
        </label>
        <br/>
        <label>
            Select type carriage:
            <select name="type">
                <option value="COUPE"> Coupe</option>
                <option value="LUX"> Lux</option>
                <option value="PLATSKART"> Platskart</option>
                <option value="SEAT_PLACE"> Seat place</option>
            </select>
        </label>
        <br/>
        <label>
            Amount seats:
            <input type="number" name="countSeats"/>
        </label>
        <br/>
        <label>
            Restaurant:
            <input type="checkbox" name="rest" value="true">
        </label>
        <br/>
        <input type="submit" value="Add train"/>
    </form>

</div>

<div id="News" class="tabcontent">
    <h3>News</h3>
    <p>Some news this fine day!</p>
</div>

<div id="Contact" class="tabcontent">
    <h3>Contact</h3>
    <p>Get in touch, or swing by for a cup of coffee.</p>
</div>

<div id="About" class="tabcontent">
    <h3>About</h3>
    <p>Who we are and what we do.</p>
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
            tablinks[i].style.backgroundColor = "";
        }
        document.getElementById(pageName).style.display = "block";
        elmnt.style.backgroundColor = color;
        elmnt.style.opacity = "0.25";
    }

    // Get the element with id="defaultOpen" and click on it
    document.getElementById("defaultOpen").click();
</script>


<jsp:include page="_footer.jsp"/>

</body>
</html>
