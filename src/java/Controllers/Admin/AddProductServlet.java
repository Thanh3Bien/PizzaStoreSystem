/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers.Admin;

import Models.Product;
import Models.ProductDAO;
import Models.ProductError;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author ADMIN
 */
@WebServlet(name = "AddProductServlet", urlPatterns = {"/AddProductServlet"})
public class AddProductServlet extends HttpServlet {

    private final String displayMessagePage = "DisplayMessage.jsp";
    private final String addProduct = "AddProduct.jsp";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String productId, productName, description, supplierId, categoryId, productImage;
        String txtQuantityPerUnit, txtUnitPrice;
        int quantityPerUnit;
        float unitPrice;
        boolean isError = false;
        String message = "Something went wrong";
        response.setContentType("text/html;charset=UTF-8");
        String url = addProduct;
        try {
            ProductError productError = new ProductError();
            ProductDAO productDAO = new ProductDAO();
            productId = request.getParameter("txtProductId");
            productName = request.getParameter("txtProductName");
            description = request.getParameter("txtDescription");
            supplierId = request.getParameter("txtsupplierID");
            categoryId = request.getParameter("txtcategoryID");
            txtQuantityPerUnit = request.getParameter("txtQuantityPerUnit");
            txtUnitPrice = request.getParameter("txtPrice");
            productImage = request.getParameter("txtImage");

            if (productId.matches("P\\d{3}") == false) {
                productError.setProductIdError("The ID must be formatted Pxxx, x is digits");
                isError = true;
            }
            if (txtQuantityPerUnit.matches("^[1-9]\\d*$") == false) {
                productError.setQuantityPerUnitError("Quantity need to be a Integer more than 0");
                isError = true;
            }
            if (txtUnitPrice.matches("^[1-9]\\d*(\\.\\d+)?$") == false) {
                productError.setUnitPriceError("Quantity need to be a Float more than 0");
                isError = true;
            }
            if (productDAO.checkProductByID(productId) == true) {
                productError.setDuplicateProductError("Product has already exsists.");
                isError = true;
            }
            if (isError == false) {
                quantityPerUnit = Integer.parseInt(txtQuantityPerUnit);
                unitPrice = Float.parseFloat(txtUnitPrice);
                Product product = new Product(productId, productName, description, supplierId, categoryId, quantityPerUnit, unitPrice, productImage);

                if (productDAO.AddProduct(product) == true) {
                    message = "The product " + productId + " has been added succesfully";
                }

            } else {
                request.setAttribute("ErrorDetailsAddProduct", productError);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            request.setAttribute("action", "Add new product");
            request.setAttribute("page", "login-success.jsp");
            request.setAttribute("addProductMessage", message);
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
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
