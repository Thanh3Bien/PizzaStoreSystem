<%@page import="Models.Category"%>
<%@page import="Models.CategoryDAO"%>
<%@page import="Models.Supplier"%>
<%@page import="java.util.List"%>
<%@page import="Models.SupplierDAO"%>
<%@ page contentType="text/html;" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Edit Product</title>
    </head>
    <body>
        <h1>Edit Product</h1>

        <%-- Hiển thị thông tin sản phẩm trong biểu mẫu --%>
        <form action="UpdateProductServlet" method="post">
            <input type="hidden" name="productId" value="${product.productID}" />
            <label for="productName">Product Name:</label>
            <input type="text" id="productName" name="productName" value="${product.productName}" /><br><br>

            <label for="description">Description:</label>
            <input type="text" id="description" name="description" value="${product.description}" /><br><br>

            <label for="supplierId">Supplier ID:</label>
            <select name="supplierId" id="supplierId">
                <%
                    SupplierDAO supplierDAO = new SupplierDAO();
                    List<Supplier> suppliers = supplierDAO.getAllSuppliers();
                    for (Supplier supplier : suppliers) {
                %>
                <option value="<%= supplier.getSupplierID() %>"><%= supplier.getSupplierID() %></option>
                <%
                    }
                %>
            </select><br><br>

            <label for="categoryId">Category ID:</label>
            <select name="categoryId" id="categoryId">
                <%
                    CategoryDAO categoryDAO = new CategoryDAO();
                    List<Category> categories = categoryDAO.getAllCategories();
                    for (Category category : categories) {
                %>
                <option value="<%= category.getCategoryID() %>"><%= category.getCategoryID() %></option>
                <%
                    }
                %>
            </select><br><br>

            <label for="quantityPerUnit">Quantity Per Unit:</label>
            <input type="text" id="quantityPerUnit" name="quantityPerUnit" value="${product.quantityPerUnit}" /><br><br>

            <label for="unitPrice">Unit Price:</label>
            <input type="text" id="unitPrice" name="unitPrice" value="${product.unitPrice}" /><br><br>

            <label for="productImage">Product Image:</label>
            <input type="text" id="productImage" name="productImage" value="${product.productImage}" /><br><br>



            <input type="submit" value="Update" />
        </form>
    </body>
</html>