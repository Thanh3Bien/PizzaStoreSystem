<%@page import="java.util.List"%>
<%@page import="Models.CartItem"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Cart</title>


    </head>
    <body>
        <h1>Your Cart</h1>
        <%-- Declare variables --%>
        <% double totalAmount = 0; %>
        <% int numberOfProducts = 0; %>
        <% List<CartItem> itemsInCart = (List<CartItem>) request.getAttribute("Cart"); %>
        <%-- Check if the cart is empty --%>
        <% if (itemsInCart == null || itemsInCart.isEmpty()) { %>
        <h3>Cart is empty!!!</h3>
        <% } else { %>
        <table border='1' style="width:300px">
            <thead>
                <tr>
                    <th>No.</th>
                    <th>Id</th>
                    <th>Description</th>
                    <th>Price</th>
                    <th>Name</th>
                    <th>Quantity</th>
                    <th>Subtotal</th>
                    <th colspan="2">Action</th>     
                </tr>
            </thead>
            <tbody>
                <%-- Iterate over items in the cart --%>
                <% int count = 0; %>
                <% for (CartItem item : itemsInCart) { %>
                <%-- Update total amount and number of products --%>
                <% totalAmount += item.getSubTotal(); %>
                <% numberOfProducts += item.getItemQuantityPerUnit();%>
            <form action="CartController" method="post">
                <tr>
                    <td><%= (++count)%></td>
                    <td><%= item.getItemID()%>
                        <input type="hidden" value='<%= item.getItemID()%>' name="ItemId"/>
                    </td>
                    <td><%= item.getItemDescription()%></td>
                    <td><%= item.getItemPrice()%>
                        <input type="hidden" value='<%= item.getItemPrice()%>' name="price"/>
                    </td>
                    <td><%= item.getItemName()%></td>
                    <td>
                        <input type="number" min="1" name="quantity" value="<%= item.getItemQuantityPerUnit()%>"/>
                    </td>
                    <td><%= String.format("%.2f", item.getSubTotal())%></td>
                    <td style="text-align: center">
                        <input type="submit" value="Remove" name="action"/>
                    </td>
                    <td style="text-align: center">
                        <input type="submit" value="Update" name="action"/>
                    </td>
                    <td style="text-align: center">
                        <input type="submit" value="Order" name="action"/>
                    </td>
                </tr>
            </form>
            <% }%>
            <tr>
                <td colspan="5" style="text-align: right"><b>Total Amount</b></td>
                <td><%= String.format("%.2f", totalAmount)%></td>
            </tr>
        </tbody>
    </table>
    <h3>Number of Products in Cart: <%= numberOfProducts%></h3>
    <% } %>
    <table>
        <tr>
            <td>
                <input type="button" value="Back to view Product" onclick="window.location.href = 'ShowProduct.jsp'">
            </td>
            <td>
                <form method="post" action="CartController">
                    <input type="submit" value="Save" name="action">
                </form>
            </td>
        </tr>
    </table>
    <% if (request.getAttribute("Message") != null) {%>
    <%= request.getAttribute("Message")%>
    <% }%>
</body>
</html>