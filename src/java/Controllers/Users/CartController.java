/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers.Users;

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
@WebServlet(name = "CartController", urlPatterns = {"/CartController"})
public class CartController extends HttpServlet {

    private final String addCartController = "AddCartController";
    private final String viewCartController = "ViewCartController";
    private final String removeCartController = "RemoveCartController";
    private final String updateCartController = "UpdateCartController";
    private final String saveCartController = "SaveCartController";
    private final String orderCartController = "OrderCartController";
    private final String addProductToOrderController = "OrderProducts";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = viewCartController, action;
        
        try {
            action = request.getParameter("action");
            if(action.equals("Add")){
                url = addCartController;
            }
            else if(action.equals("View Cart")){
                url = viewCartController;
            }
            else if(action.equals("Remove")){
                url = removeCartController;
            }
            else if(action.equals("Update")){
                url = updateCartController;
            }
            else if(action.equals("Save")){
                url = saveCartController;
            }
            else if(action.equals("Create Order")){
                url = orderCartController;
            }
            else if(action.equals("Order")){
                url = addProductToOrderController;
            }
        }
        catch(Exception ex){
            log("CartController has error:"+ ex.getMessage());
        }
        finally{
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
