/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers.Users;

import Models.CartItem;
import Models.Product;
import Models.ProductDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "AddCartController", urlPatterns = {"/AddCartController"})
public class AddCartController extends HttpServlet {

    private final String productController = "ShowProduct.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = null;
        String message, productId;
        Product selectedProduct = null;
        CartItem item = null;
        HashMap<String, CartItem> itemsInCart = null;
        try {
            ProductDAO productDAO = new ProductDAO();
            HttpSession shoppingCart = request.getSession();
            itemsInCart = (HashMap<String, CartItem>) shoppingCart.getAttribute("Cart");
            productId = request.getParameter("ProductId");
            selectedProduct = productDAO.getProductById(productId);
            if (itemsInCart == null) {
                itemsInCart = new HashMap<String, CartItem>();
                shoppingCart.setAttribute("Cart", itemsInCart);
                HttpSession session = request.getSession();
                session.setAttribute("Cart", itemsInCart);
            }
            item = itemsInCart.get(selectedProduct.getProductID());
            if (item == null) {
                item = new CartItem(
                        selectedProduct.getProductID(),
                        selectedProduct.getProductName(),
                        selectedProduct.getDescription(),
                        selectedProduct.getSupplierID(),
                        selectedProduct.getCategoryID(),
                        selectedProduct.getQuantityPerUnit(),
                        selectedProduct.getUnitPrice(),
                        selectedProduct.getProductImage()
                );
                itemsInCart.put(item.getItemID(), item);
                message = "The product " + item.getItemName() + " has been added to cart successfully.";
            } else {
                item.setItemQuantityPerUnit(item.getItemQuantityPerUnit() + 1);
                message = "The cart has been updated successfully.";
            }
            url = productController + "?action=ViewProductList";
            request.setAttribute("Message", "<h4>" + message + "</h4>");
        } catch (Exception ex) {
            log("AddCartController has error: " + ex.getMessage());
        } finally {
//            RequestDispatcher rd = request.getRequestDispatcher(url);
//            rd.forward(request, response);

            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            PrintWriter out = response.getWriter();
            out.print("{\"message\": \"The mobile has been added to cart successfully.\"}");
            out.flush();
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
