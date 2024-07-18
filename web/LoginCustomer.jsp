<%-- 
    Document   : LoginCustomer.jsp
    Created on : Mar 10, 2024, 10:25:17 AM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Login Customer</title>

        <link rel="stylesheet" type="text/css" href="Styles.css">
    </head>
    <body>
        <div class="theme-bar">
            <div class="left">
                <h2 style="color: red">PizzaStore</h2>
                <a href="ShowProduct.jsp">Pizza</a>
                <a href="ShowProduct.jsp">Categories</a>
            </div>
            <div class="right">
                <%
                    boolean isLoggedIn = (session.getAttribute("loggedInAccount") != null);
                    boolean isCustomerLoggedIn = (session.getAttribute("customerID") != null);

                    if (isLoggedIn || isCustomerLoggedIn) {
                %>
                <a href="LogOutController">Logout</a>
                <%
                } else {
                %>
                <a href="Register.jsp">Register</a>
                <a href="LoginHome.jsp">Login</a>
                <%
                    }
                %>
            </div>
        </div>

        <h1>Login As Customer</h1>

        <form action="LoginController" method="post">
            <label for="username">CustomerID:</label>
            <input type="text" id="customerID" name="customerID" required><br><br>

            <label for="password">Password:</label>
            <input type="password" id="customerPassword" name="customerPassword" required><br><br>

            <input type="submit" value="Login">
        </form>
        <h4>Do not have an account?</h4>
        <a href="Register.jsp" class="button">Register</a>
    </body>
</html>
