/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers.Users;

import Models.OrderDetail;
import Models.OrderDetailDAO;
import java.io.IOException;
import java.io.PrintWriter;
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
@WebServlet(name = "OrderProducts", urlPatterns = {"/OrderProducts"})
public class OrderProducts extends HttpServlet {

    private final String createOrder = "OrderPage.jsp";
    private final String displayMessage = "DisplayMessage.jsp";
    private final String backLogin = "LoginCustomer.jsp";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String orderID, productID;
        float unitPrice;
        int quantity;

        String message = "Something went wrong";
        response.setContentType("text/html;charset=UTF-8");
        String url = "";

        try {
            HttpSession session = request.getSession();
            String checkorderID = (String) session.getAttribute("hasOrder");
            String checkLogin = (String) session.getAttribute("customerID");
            if(checkLogin == null && checkorderID == null){
                url = backLogin;
            }
            else if (checkorderID == null && checkLogin != null) {
                url = createOrder;
            }
            
            else if (checkorderID != null && checkLogin != null){
                orderID = (String) session.getAttribute("orderID");
                productID = request.getParameter("ItemId");
                unitPrice = Float.parseFloat(request.getParameter("price"));
                quantity = Integer.parseInt(request.getParameter("quantity"));
                message = "Ok rồi mà";
                if (orderID != null) {
                    OrderDetail orderDetail = new OrderDetail(orderID, productID, unitPrice, quantity);
                    OrderDetailDAO orderDetailDAO = new OrderDetailDAO();
                    message = "Vẫn ok";
                    if (orderDetailDAO.addDetail(orderDetail) == true) {
                        message = "The product " + orderID + " has been added succesfully";
                        url = displayMessage;
//                    session.setAttribute("orderID", orderID);
                    }

                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            request.setAttribute("action", "Add new product");
            request.setAttribute("page", "ShowProduct.jsp");
            request.setAttribute("message", message);
//            request.setAttribute("hasOrder", "hasOrder");
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
