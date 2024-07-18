<%-- 
    Document   : AddProduct
    Created on : Mar 4, 2024, 2:03:24 PM
    Author     : ADMIN
--%>

<%@page import="Models.Category"%>
<%@page import="Models.CategoryDAO"%>
<%@page import="Models.Supplier"%>
<%@page import="java.util.List"%>
<%@page import="Models.SupplierDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>

        <link rel="stylesheet" type="text/css" href="Styles.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Product Page</title>
        <!--        <script>
                    function validateIntegerInput(inputElement, fieldName) {
                        var value = inputElement.value;
        
                        if (value.trim() === "" || Number.isNaN(Number(value)) || !Number.isInteger(Number(value))) {
                            alert("Please enter a valid " + fieldName + " (integer).");
                            inputElement.value = "";
                        }
                    }
                    function validateFloatInput(inputElement, fieldName) {
                        var value = inputElement.value;
        
                        if (value.trim() === "" || isNaN(Number(value))) {
                            alert("Please enter a valid " + fieldName + " (float).");
                            inputElement.value = "";
                        }
                    }
                    function validatePrice() {
                        var priceInput = document.getElementById("priceInput");
                        var price = priceInput.value;
        
                        if (!isBigDecimal(price)) {
                            alert("Please enter a valid Price (BigDecimal).");
                            priceInput.value = "";
                        }
                    }
        
        
        //            function updateNotSale(checkbox) {
        //                var notSale = checkbox.checked;
        //                // Xử lý giá trị notSale ở đây
        //            }
                </script>-->
    </head>
    <body>
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
        <h1>Create Product</h1>
        <c:set var="message" value="${requestScope.message}"/>
        <c:if test="${message!= null}">
            ${message}<br>
        </c:if>
        <form action="AddProductServlet" method="post">
            <c:set var="error" value="${requestScope.ErrorDetailsAddProduct}"/>
            <c:if test="${not empty error.duplicateProductError}">
                <text style="color: red">${error.duplicateProductError}</text><br>
            </c:if>

            <label for="productid">ProductID:</label>
            <input type="text" id="productID" placeholder="Pxxx" name="txtProductId" required><br><br>
            <c:if test="${not empty error.productIdError}">
                <text style="color: red">${error.productIdError}</text><br><br>
            </c:if>
            <!--            ProductID <input type="text" placeholder="Enter productId" name="txtProductId" /><br/>-->

            <label for="productname">ProductName:</label>
            <input type="text"  name="txtProductName" required><br/><br>
            <label for="description">Description:</label>
            <input type="text"  name="txtDescription" required><br/><br>
            <label for="supplierid">SupplierID:</label>
            <select name="txtsupplierID">
                <%
                    SupplierDAO supplierDAO = new SupplierDAO();
                    List<Supplier> suppliers = supplierDAO.getAllSuppliers();
                    for (Supplier supplier : suppliers) {
                %>
                <option value="<%= supplier.getSupplierID()%>"><%= supplier.getSupplierID()%></option>
                <%
                    }
                %>
            </select><br/><br>

            <label for="categoryid">CagoryID:</label>
            <select name="txtcategoryID">
                <%
                    CategoryDAO categoryDAO = new CategoryDAO();
                    List<Category> categories = categoryDAO.getAllCategories();
                    for (Category category : categories) {
                %>
                <option value="<%= category.getCategoryID()%>"><%= category.getCategoryID()%></option>
                <%
                    }
                %>
            </select><br/><br>

            <label for="quantityperunit">QuantityPerUnit</label>
            <input type="text" id="quantityperunit" placeholder="Interger number > 0" name="txtQuantityPerUnit" required><br><br>
            <c:if test="${not empty error.quantityPerUnitError}">
                <text style="color: red">${error.quantityPerUnitError}</text><br><br>
            </c:if>
            <!--            QuantityPerUnit <input type="text" placeholder="Enter QuantityPerUnit" name="txtQuantityPerUnit"  onblur="validateIntegerInput(this, 'Quantity')"/><br/>-->

            <label for="unitprice">UnitPrice:</label>
            <input type="text" id="unitprice" placeholder="Number > 0" name="txtPrice" required><br><br>
            <c:if test="${not empty error.unitPriceError}">
                <text style="color: red">${error.unitPriceError}</text><br>
            </c:if>

            <!--        UnitPrice <input type="text" placeholder="Enter Price" name="txtPrice"  onblur="validateFloatInput(this, 'Price')"/><br/>-->
            <label for="productimage">Product Image:</label>
            <input type="text"  name="txtImage" required><br/>
            <br>
            <!--            Not for sale <input type="checkbox" name="chkNotSale" onchange="updateNotSale(this)" /><br/>-->

            <a href="LoginHome.jsp">Back to Login</a>
            <input type="submit" value="Add" name="btnAction"/>
        </form>
    </body>
</html>
