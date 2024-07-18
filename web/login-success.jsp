

<%@page import="Models.Product"%>
<%@page import="Models.ProductDAO"%>
<%-- 
    Document   : CarDetail
    Created on : Feb 6, 2024, 9:20:42 AM
    Author     : ADMIN
--%>



<%@page import="java.util.List"%>
<%@page contentType="text/html;" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="Styles.css">
        <meta http-equiv="Content-Type" content="text/html;  charset=UTF-8">
        
        <title>Product List</title>
    </head>


    <div class="theme-bar">
        <div class="left">
            <h2 style="color: red">PizzaStore</h2>
            <a href="ShowProduct.jsp">Pizza</a>
            <a href="ShowProduct.jsp">Categories</a>
            <a href="login-success.jsp">Product List</a>
        </div>
        <div class="right">
            <%
                boolean isLoggedIn = (session.getAttribute("loggedInAccount") != null);
                boolean isCustomerLoggedIn = (session.getAttribute("customerID") != null);

                if (isLoggedIn || isCustomerLoggedIn) {
            %>
            <p style="color: blue">Hi, ${user.userName}</p>
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
  
    <h1 >Product List</h1>
    <a href="AddProduct.jsp" id="add-car-btn">Add Product</a>   
    <table>
        <tr>
            <th>Name</th>
            <th>Price</th>
            <th>Description</th>
            <th>ImageUrl</th>
            <th>Category</th>
            <th colspan="2">Actions</th> <!-- Thêm cột mới cho nút "Update" -->
        </tr>

        <%-- Sử dụng JSP Expression để lặp qua danh sách xe và hiển thị thông tin --%>
        <%
//            List<Car> carList = (List<Car>) request.getAttribute("carList");
            ProductDAO productDAO = new ProductDAO();
            List<Product> productList = productDAO.getProductList();

            if (productList == null) { %>

        <tr>
            <td colspan="5" style="color: red;">Danh sách sản phẩm không có sẵn.</td>
        </tr>
        <% } else { %>
        <% for (Product product : productList) {%>
        <td><%= product.getProductName()%></td>
        <td><%= product.getUnitPrice()%></td>
        <td><%= product.getDescription()%></td>
        <td><%= product.getProductImage()%></td>
        <td><%= product.getCategoryID()%></td>

        <td>
            <a href="EditProductServlet?ProductId=<%=product.getProductID()%>">Update</a>  
            
        </td>
        <td>
            
            <a href="DeleteProduct.jsp?ProductId=<%=product.getProductID()%>">Delete</a>
        </td>
    </tr>
    <% } %>
    <% }%>

</table>
</html>

