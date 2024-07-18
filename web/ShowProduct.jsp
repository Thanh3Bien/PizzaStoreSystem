<%-- 
    Document   : ShowProduct
    Created on : Mar 9, 2024, 1:09:25 PM
    Author     : ADMIN
--%>



<%--
    Document   : Search
    Created on : Feb 18, 2024, 4:15:40 PM
    Author     : ADMIN
--%>

<%@page import="Models.Product"%>
<%@page import="Models.ProductDAO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search Product</title>
        <link rel="stylesheet" type="text/css" href="Styles.css">
        <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
        <script>
            $(document).ready(function () {
                $(".addToCart").click(function (e) {
                    e.preventDefault();
                    var productId = $(this).data("product-id");
                    $.ajax({
                        url: "AddCartController",
                        type: "POST",
                        data: {ProductId: productId},
                        success: function (response) {
                            alert("Added to cart successfully.");
                        },
                        error: function (xhr, status, error) {
                            alert("An error occurred while adding to cart.");
                        }
                    });
                });
            });
        </script>
        <script>
            function showMessage(message) {
                var messageElement = document.createElement("div");
                messageElement.innerHTML = message;
                document.body.appendChild(messageElement);
                setTimeout(function () {
                    document.body.removeChild(messageElement);
                }, 3000);
            }
        </script>
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
        <%
            ProductDAO productDAO = new ProductDAO();
            List<Product> productList = productDAO.getProductList();
        %>

        <h1>Pizzas Menu</h1>

        <form action="SearchServletUsers" method="post">
            Search by name
            <input type="text" name="txtSearchProduct" value=""/><br/>
            <input type="submit" value="Search" name="btnAction"/>
        </form>
        <h3>All pizzas</h3>
        <%
            String searchProduct = request.getParameter("txtSearchProduct");
            if (searchProduct != null && !searchProduct.isEmpty()) {
                productList = productDAO.searchProductByFullName(searchProduct);
            }

            if (productList != null && !productList.isEmpty()) {
        %>
        <div class="product-grid">
            <% for (Product product : productList) { %>
                <div class="product-card">
                    <div class="product-image">
                        <img src="<%=product.getProductImage()%>" alt="Product Image" width="150" height="150">
                    </div>
                    <div class="product-name">
                        <%=product.getProductName()%>
                    </div>
                    <div class="product-description">
                        <%=product.getDescription()%>
                    </div>
                    <div class="product-price">
                        <%=product.getUnitPrice()%>
                    </div>
                    <a href="#" class="add-to-cart-btn addToCart" data-product-id="<%=product.getProductID()%>">Add Pizza</a>
                </div>
            <% } %>
        </div>
        <h3>Number of products found: <%=productList.size()%> </h3>
        <% } else { %>
        <h3>No products found.</h3>
        <% } %>
        <form action="CartController" class="button">
            <input type="submit" value="View Cart" name="action"/>
        </form>
        <a href="LoginHome.jsp" class="button">Back to Login</a>
    </body>
</html>