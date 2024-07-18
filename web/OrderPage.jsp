<%-- 
    Document   : OtherPage
    Created on : Mar 10, 2024, 11:39:05 AM
    Author     : ADMIN
--%>

<%@ page import="java.sql.*" %>
<%@ page import="java.util.*" %>
<%@ page import="java.text.*" %>
<%@ page import="java.util.Date" %>
<%@ page contentType="text/html; charset=UTF-8" %>


<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    // Kết nối cơ sở dữ liệu
    Connection conn = null;
    try {
        // Code để thiết lập kết nối cơ sở dữ liệu
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String connectionString = "jdbc:sqlserver://localhost:1433;database=PizzaStore";
        conn = DriverManager.getConnection(connectionString, "sa", "12345");

        // Lấy Order ID mới
        String orderID = "";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT MAX(OrderID) FROM Orders");
        if (rs.next()) {
            String lastOrderID = rs.getString(1);
            // Tạo Order ID mới
            int lastID = Integer.parseInt(lastOrderID.substring(1));
            int newID = lastID + 1;
            orderID = "O" + String.format("%04d", newID);
        } else {
            orderID = "O0001";
        }
        rs.close();
        stmt.close();

        // Lấy Customer ID từ đăng nhập
        String customerID = (String) session.getAttribute("customerID"); // Thay bằng mã khách hàng từ đăng nhập

        // Lấy ngày hiện tại
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String orderDate = dateFormat.format(currentDate);
%>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Other Page</title>
        <link rel="stylesheet" type="text/css" href="Styles.css">
        <style>
            /* Container cho mỗi thuộc tính */
            .container {
                display: flex;
                justify-content: center;
                align-items: center;
                height: 50vh;
            }

            .form-row {
                display: flex;
                align-items: center;
                gap: 20px;
                margin-bottom: 10px;
            }

            /* Nhãn thuộc tính */
            .form-row label {
                display: inline-block;
                width: 120px;
                text-align: right;
            }

            /* Trường nhập liệu thuộc tính */
            .form-row input[type="text"] {
                width: 200px;
            }

            /* Nút Submit */
            .form-row input[type="submit"] {
                margin-left: 120px;
            }
        </style>
    </head>
    <body>
        <h1 style="text-align: center; color: red" >Create Order</h1>


        <a href="ViewCart.jsp" class="button" >View Product</a>

        <div class="container">
            <form action="AddOrderController" method="post">
                <c:set var="error" value="${requestScope.ErrorDetailsAddOrder}"/>
                <c:set var="message" value="${requestScope.addOrderMessage}"/>
                <c:if test="${message!= null}">
                    ${message}<br>
                </c:if>
                
                <div class="form-row">
                    <label for="orderID">Order ID:</label>
                    <input type="text" id="orderID" name="orderID" value="<%= orderID%>" readonly>
                </div>

                <div class="form-row">
                    <label for="customerID">Customer ID:</label>
                    <input type="text" id="customerID" name="customerID" value="<%= customerID%>" readonly>
                </div>

                <div class="form-row">
                    <label for="orderDate">Order Date:</label>
                    <input type="text" id="orderDate" name="orderDate" value="<%= orderDate%>" readonly>
                </div>

                <div class="form-row">
                    <label for="requiredDate">Required Date:</label>
                    <input type="text" id="requiredDate" name="requiredDate">
                    <c:if test="${not empty error.requiredDateError}">
                        <div class="error-message" style="color: red">${error.requiredDateError}</div>
                    </c:if>
                </div>

                <div class="form-row">
                    <label for="shippedDate">Shipped Date:</label>
                    <input type="text" id="shippedDate" name="shippedDate">
                    <c:if test="${not empty error.shippedDateError}">
                        <div class="error-message" style="color: red">${error.shippedDateError}</div>
                    </c:if>
                </div>

                <div class="form-row">
                    <label for="freight">Freight:</label>
                    <input type="text" id="freight" name="freight">
                    <c:if test="${not empty error.frieghtError}">
                        <div class="error-message" style="color: red">${error.frieghtError}</div>
                    </c:if>
                </div>

                <div class="form-row">
                    <label for="shipAddress">Ship Address:</label>
                    <input type="text" id="shipAddress" name="shipAddress">
                </div>

                <div class="form-row">
                    <c:if test="${not empty error.logicError}">
                        <div class="error-message" style="color: red">${error.logicError}</div>
                    </c:if>
                </div>


                <div class="form-row">
                    <input type="submit" value="Create Order">
                </div>

        </div>

    </form>

</body>
</html>


<%
    } catch (SQLException e) {
        e.printStackTrace();
    } finally {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
%>

