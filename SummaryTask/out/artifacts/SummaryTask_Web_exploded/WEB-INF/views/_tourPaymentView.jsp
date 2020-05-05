<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%--
  Created by IntelliJ IDEA.
  User: karina
  Date: 04.05.2020
  Time: 00:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/style/payment.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<fmt:setLocale value="${language}"/>
<html>

<body>

<div id="id02" class="modal">

    <fmt:bundle basename="staticInformation">
        <form class="modal-content animate" id="payment_form">
                <span onclick="closeBuyForm()"
                      class="close" title="Close Modal">&times;</span>
            <div class="container">
                <div class="col-50">
                    <h3><fmt:message key="payment"/></h3>
                    <label><fmt:message key="cards"/></label>
                    <div class="icon-container">
                        <i class="fa fa-cc-visa" style="color:navy;"></i>
                        <i class="fa fa-cc-amex" style="color:blue;"></i>
                        <i class="fa fa-cc-mastercard" style="color:red;"></i>
                        <i class="fa fa-cc-discover" style="color:orange;"></i>
                    </div>
                    <label for="ccnum"><fmt:message key="card_number"/></label>
                    <input type="number" id="ccnum" name="cardnumber" placeholder="1111-2222-3333-4444" required>
                    <label for="expmonth"><fmt:message key="expm"/></label>
                    <input type="text" id="expmonth" name="expmonth" required>
                    <div class="row">
                        <div class="col-50">
                            <label for="expyear"><fmt:message key="expy"/></label>
                            <input type="number" id="expyear" name="expyear" required>
                        </div>
                        <div class="col-50">
                            <label for="cvv">CVV</label>
                            <input type="number" id="cvv" name="cvv" required>
                        </div>
                    </div>
                </div>

            </div>
            <input type="submit" id="subm" value="<fmt:message key="buy"/>" class="btn" form="buy_form">
        </form>
    </fmt:bundle>
</div>

</body>

<script>
    // Get the modal
    var modal = document.getElementById('id02');

    // When the user clicks anywhere outside of the modal, close it
    window.onclick = function (event) {
        if (event.target == modal) {
            modal.style.display = "none";
        }
    };

    function closeBuyForm() {
        document.getElementById('id02').style.display = 'none';
    }
</script>

</html>
