<%-- 
    Document   : login-process
    Created on : Feb 28, 2024, 4:25:09 PM
    Author     : ADMIN
--%>

<%@page import="Models.Account"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <jsp:useBean id="user" class="Models.Account" scope="session"/>
        <jsp:setProperty name="user" property="userName" value="${param.userName}"/>
        <jsp:setProperty name="user" property="password" value="${param.password}"/>
        <%
            Account accountLoggedIn = user.validate();
            if (accountLoggedIn != null && accountLoggedIn.getType() == true) {
                session.setAttribute("loggedInAccount", accountLoggedIn);
        %>
        <jsp:forward page="login-success.jsp">
            <jsp:param name="fullName" value="<%=accountLoggedIn.getFullName()%>"/>
        </jsp:forward>
        <%
        } else if (accountLoggedIn != null && accountLoggedIn.getType() == false) {
            session.setAttribute("loggedInAccount", accountLoggedIn);
        %>
        <jsp:forward page="login-success-user.jsp">
            <jsp:param name="fullName" value="<%=accountLoggedIn.getFullName()%>"/>
        </jsp:forward>
        <%
            }
        %>
        <jsp:forward page="login-error.jsp"/>
    </body>
</html>
