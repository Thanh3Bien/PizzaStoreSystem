<%-- 
    Document   : Register
    Created on : Mar 14, 2024, 1:53:16 PM
    Author     : ADMIN
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="Styles.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register</title>
    </head>
    <body>
        <h1>Register</h1>
        <c:set var="message" value="${requestScope.message}"/>
        <c:if test="${message!= null}">
            ${message}<br>
            
        </c:if>
        <form action="RegisterController" method="post">
            <c:set var="error" value="${requestScope.ErrorDetails}"/>
            <c:if test="${not empty error.duplicateCustomerError}">
                <text style="color: red">${error.duplicateCustomerError}</text>
            </c:if>
                <br><br>
            <label for="userid">CustomerID:</label>
            <input type="text" id="customerID" name="customerID" required><br><br>
            <c:if test="${not empty error.customerIdError}">
                <text style="color: red">${error.customerIdError}</text><br><br>
            </c:if>
            
            <label for="password">Password:</label>
            <input type="password" id="customerPassword" name="customerPassword" required><br><br>

            <label for="contactname">ContactName:</label>
            <input type="text" name="contactName" required><br><br>

            <label for="address">Address:</label>
            <input type="text" name="address" required><br><br>
            <c:set var="error" value="${requestScope.ErrorDetails}"/>
            <label for="phone">Phone:</label>

            <input type="text" name="phone" required><br><br>
            <c:if test="${not empty error.phoneError}">
                <text style="color: red">${error.phoneError}</text><br><br>
            </c:if>
            
            
            <a href="LoginHome.jsp" class="button">Back to Login</a>
            <input type="submit" value="Register" class="button">
           
        </form>
    </body>
</html>
