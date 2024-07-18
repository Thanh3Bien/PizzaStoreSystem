/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers.Users;

import Models.Order;
import Models.OrderDAO;
import Models.OrderDetail;
import Models.OrderDetailDAO;
import Models.OrderError;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
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
@WebServlet(name = "AddOrderController", urlPatterns = {"/AddOrderController"})
public class AddOrderController extends HttpServlet {

    private final String displayMessagePage = "DisplayMessage.jsp";
    private final String orderPage = "OrderPage.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String orderID, customerID, orderDate, requiredDate, shippedDate, shipAddress, txtFreight;
        BigDecimal freight;
        boolean isError = false;

        String message = "";
        response.setContentType("text/html;charset=UTF-8");
        String url = orderPage;
//        String[] productIDs, unitPrices, quantities;
        try {
            OrderError orderError = new OrderError();
            OrderDAO orderDAO = new OrderDAO();
            orderID = request.getParameter("orderID");
            customerID = request.getParameter("customerID");
            orderDate = request.getParameter("orderDate");
            requiredDate = request.getParameter("requiredDate");
            shippedDate = request.getParameter("shippedDate");
            txtFreight = request.getParameter("freight");
            shipAddress = request.getParameter("shipAddress");

            if (isValidSingleDate(requiredDate) == false) {
                orderError.setRequiredDateError("Date is invalid.");
                isError = true;
            }
            if (isValidSingleDate(shippedDate) == false) {
                orderError.setShippedDateError("Date is invalid.");
                isError = true;
            }
            if (isValidSingleDate(requiredDate) == true && isValidSingleDate(shippedDate) == true) {
                if (isValidDate(orderDate, requiredDate, shippedDate) == false) {
                    orderError.setLogicError("ShippedDate need to aftered RequiredDate");
                    isError = true;
                }
            }
            if (txtFreight.matches("\\d+")) {
                // Chuỗi chỉ chứa các chữ số
                freight = new BigDecimal(txtFreight);
                if (freight.compareTo(BigDecimal.ZERO) < 0) {
                    orderError.setFrieghtError("Freight > 0");
                    isError = true;
                }
            }
            else{
                isError = true;
               orderError.setFrieghtError("Freight is invalid."); 
            }
            if (isError == false) {
                freight = new BigDecimal(txtFreight);
                Order order = new Order(orderID, customerID, orderDate, requiredDate, shippedDate, freight, shipAddress);

                if (orderDAO.AddOrder(order) == true) {
                    message = "The order " + orderID + " has been added succesfully";
                    HttpSession session = request.getSession();
                    session.setAttribute("orderID", orderID);
                    session.setAttribute("hasOrder", "hasOrder");
                }

            } else {
                request.setAttribute("ErrorDetailsAddOrder", orderError);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            request.setAttribute("action", "Add new product");
            request.setAttribute("page", "ShowProduct.jsp");
            request.setAttribute("addOrderMessage", message);
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
        }
    }

    private boolean isValidDate(String orderDateString, String requiredDateString, String shippedDateString) {
        // Kiểm tra tính hợp lệ của các ngày tháng
        LocalDate orderDate = null;
        LocalDate requiredDate = null;
        LocalDate shippedDate = null;

        // Chuyển đổi chuỗi thành LocalDate nếu chuỗi không rỗng
        if (orderDateString != null && !orderDateString.isEmpty()) {
            orderDate = LocalDate.parse(orderDateString);
        }
        if (requiredDateString != null && !requiredDateString.isEmpty()) {
            requiredDate = LocalDate.parse(requiredDateString);
        }
        if (shippedDateString != null && !shippedDateString.isEmpty()) {
            shippedDate = LocalDate.parse(shippedDateString);
        }

        // Kiểm tra shippedDate sau hoặc cùng ngày với requiredDate
        boolean isRequiredDateValidWithOrderDate = requiredDate == null || requiredDate.isAfter(orderDate) || requiredDate.isEqual(orderDate);
        boolean isShippedDateValidWithRequiredDate = shippedDate == null || shippedDate.isAfter(requiredDate) || shippedDate.isEqual(requiredDate);

        // Trả về kết quả cuối cùng
        return isShippedDateValidWithRequiredDate && isRequiredDateValidWithOrderDate;
    }

    private boolean isValidSingleDate(String dateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        try {
            LocalDate.parse(dateString, formatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
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
