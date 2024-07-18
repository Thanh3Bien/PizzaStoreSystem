<%-- 
    Document   : DeleteProduct
    Created on : Mar 4, 2024, 3:29:06 PM
    Author     : ADMIN
--%>


<%@page import="Models.Product"%>
<%@page import="Models.ProductDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="Style.css">
    <title>Delete Product</title>
</head>
<body>
    <h1>Delete Product</h1>

    <%-- Lấy thông tin của xe từ request parameter --%>
    <% 
        String productId = request.getParameter("ProductId");
        ProductDAO productDAO = new ProductDAO();
        Product product = productDAO.getProductById(productId);
        
        if (product == null) { %>
            <p style="color: red;">Car not found</p>
        <% } else { %>
            <p>Are you sure want to delete Product?</p>
            <p>Product ID: <%= productId %></p>
            <p>Product Name: <%= product.getProductName() %></p>
            <p>Product Description: <%= product.getDescription() %></p>
            <p>SupplierID: <%= product.getSupplierID() %></p>
            <p>CategoryID: <%= product.getCategoryID() %></p>
            <p>QuantityPerUnit: <%= product.getQuantityPerUnit() %></p>
            <p>Price: <%= product.getUnitPrice() %></p>
            <p>Image: <%= product.getProductImage() %></p>
            
            <form action="DeleteProductServlet" method="post">
                <input type="hidden"  name="ProductId" value="<%= product.getProductID() %>">
                <input type="submit"   value="Delete" >
                <a href="login-success.jsp">Back</a>
            </form>
        <% } %>
</body>
</html>

