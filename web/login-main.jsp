<%-- 
    Document   : login-main
    Created on : Mar 4, 2024, 12:26:28 PM
    Author     : ADMIN
--%>


<!DOCTYPE html>
<html>
    <head>
        <title>Login Page</title>
        <link rel="stylesheet" type="text/css" href="Styles.css">
    </head>
    <body>
        <div class="theme-bar">
            <div class="left">
                <h2>PizzaStore</h2>
                <a href="ShowProduct.jsp">Pizza</a>
                <a href="ShowProduct.jsp">Categories</a>
            </div>
            <div class="right">
                <a href="register.html">Register</a>
                <a href="LoginHome.jsp">Login</a>
            </div>
        </div>
        <div>
            <form action="login-process.jsp" method="post">
                UserName:<input type="text" name="userName"><br>
                Password:<input type="password" name="password"><br>
                <input type="submit" value="Login">
            </form>
        </div>
    </body>
</html>
