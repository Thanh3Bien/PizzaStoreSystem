/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers.Users;

import Models.Customer;
import Models.CustomerDAO;
import Models.CustomerError;
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
@WebServlet(name = "RegisterController", urlPatterns = {"/RegisterController"})
public class RegisterController extends HttpServlet {
    private final String displayMessage = "DisplayMessage.jsp";
    private final String registerPage = "Register.jsp";
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String customerID, password, contactName, address, phone;
        boolean isError = false;
        String message = "";
        String url = registerPage;
        
        try{
            CustomerError customerError = new CustomerError();
            CustomerDAO customerDAO = new CustomerDAO();
            customerID = request.getParameter("customerID");
            password = request.getParameter("customerPassword");
            contactName = request.getParameter("contactName");
            address = request.getParameter("address");
            phone = request.getParameter("phone");
            if(customerID.matches("C\\d{3}")==false){
                customerError.setCustomerIdError("The ID must be formatted Cxxx, x is digits");
                isError = true;
            }
            if(phone.matches("^0\\d{9,10}$")==false){
                customerError.setPhoneError("The phone must be begin with 0 and length from 10 to 11 characters");
                isError = true;
            }
            if(customerDAO.checkCustomerByID(customerID)==true){
                customerError.setDuplicateCustomerError("The Customer already exists");
                isError = true;
            }
            if(isError==false){
                Customer customer = new Customer(customerID, password, contactName, address, phone);
                if(customerDAO.addCustomer(customer)==true){
                    message = "The customer has been created successfully. Please login again!";
                }
                else{
                    message = "System error. Please try later!";
                }
            }
            else{
                request.setAttribute("ErrorDetails", customerError);
            }
            
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
        finally{
            request.setAttribute("message", message);
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
