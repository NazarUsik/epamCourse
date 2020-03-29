<%--
  Created by IntelliJ IDEA.
  User: Nazar
  Date: 21.03.2020
  Time: 19:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/loginStyle.css">
<br>

<div class="form-popup" id="myForm">
    <form action="${pageContext.request.contextPath}/login" method="post" class="form-container">
        <h1>Login</h1>
        <p style="color: red;">${errorString}</p
        <br/>
        <label for="email"><b>Email</b></label>
        <input type="text" placeholder="Enter Email" id="email" name="email" required>

        <label for="psw"><b>Password</b></label>
        <input type="password" placeholder="Enter Password" id="psw" name="password" required>

        <label>
            <input type="checkbox" checked="checked" name="remember"> Remember me
        </label>

        <button type="submit" class="btn">Login</button>
        <button type="button" class="btn cancel" onclick="closeForm()">Close</button>
    </form>
</div>

<script>
    function openForm() {
        document.getElementById("myForm").style.display = "block";
    }

    function closeForm() {
        document.getElementById("myForm").style.display = "none";
    }
</script>

<%--<div id="id01" class="modal">--%>

<%--    <form class="modal-content animate" action="${pageContext.request.contextPath}/login" method="post">--%>
<%--        <div class="imgcontainer">--%>
<%--                <span onclick="document.getElementById('id01').style.display='none'"--%>
<%--                      class="close" title="Close Modal">&times;</span>--%>
<%--        </div>--%>

<%--        <div class="container">--%>

<%--            <input type="hidden" name="redirectId" value="${param.redirectId}"/>--%>
<%--            <label for="email"><b>Email</b></label>--%>
<%--            <input type="text" placeholder="Enter Email" name="email" id="email" required>--%>

<%--            <label for="psw"><b>Password</b></label>--%>
<%--            <input type="password" placeholder="Enter Password" name="password" id="psw" required>--%>

<%--            <button type="submit">Login</button>--%>
<%--            <label>--%>
<%--                <input type="checkbox" checked="checked" name="remember"> Remember me--%>
<%--            </label>--%>
<%--        </div>--%>

<%--        <div class="container" style="background-color:#f1f1f1">--%>
<%--            <button type="button" onclick="document.getElementById('id01').style.display='none'" class="cancelbtn">--%>
<%--                Cancel--%>
<%--            </button>--%>
<%--            <span class="psw">Forgot <a href="#">password?</a></span>--%>
<%--        </div>--%>
<%--    </form>--%>
<%--</div>--%>

<%--<script>--%>
<%--    // Get the modal--%>
<%--    var modal = document.getElementById('id01');--%>

<%--    // When the user clicks anywhere outside of the modal, close it--%>
<%--    window.onclick = function (event) {--%>
<%--        if (event.target == modal) {--%>
<%--            modal.style.display = "none";--%>
<%--        }--%>
<%--    }--%>
<%--</script>--%>

