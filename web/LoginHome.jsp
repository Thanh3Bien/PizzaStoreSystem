<%-- 
    Document   : LoginHome
    Created on : Mar 12, 2024, 9:31:13 AM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Login Selection</title>
        <link rel="stylesheet" type="text/css" href="Styles.css">
        <style>
            h1 {
                color: red;
                text-align: center;
            }
        </style>

    </head>
    <body>
        <div class="theme-bar">
            <div class="left">
                <h2 style="color: red">PizzaStore</h2>
                <a href="ShowProduct.jsp">Pizza</a>
                <a href="ShowProduct.jsp">Categories</a>
            </div>
            <div class="right">
                <a href="Register.jsp">Register</a>
                <a href="LoginHome.jsp">Login</a>
            </div>
        </div>
        <h1>Login Page</h1>
        <div class="container">
            <a href="LoginCustomer.jsp">Login as Customer</a>
            <a href="login-main.jsp">Login as Admin</a>
        </div>
    </body>
</html>
