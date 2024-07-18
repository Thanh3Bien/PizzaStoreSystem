/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers.Users;

import Models.Product;
import Models.ProductDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
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
@WebServlet(name = "SearchServletUsers", urlPatterns = {"/SearchServletUsers"})
public class SearchServletUsers extends HttpServlet {

    private final String searchPage = "ShowProduct.jsp";
    private final String searchPage2 = "DisplayMessage.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String url, fullName;
        String searchName;
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        url = searchPage;
        try {
            searchName = request.getParameter("txtSearchProduct");

            fullName = request.getParameter("fullName");
            request.setAttribute("fullName", fullName);
            request.setAttribute("searchProduct", searchName);

            if (searchName != null) {
                ProductDAO productDAO = new ProductDAO();
                List<Product> productList = productDAO.searchProductByFullName(searchName);
                request.setAttribute("SearchResult", productList);
            } else {
                url = searchPage2;
            }
        } catch (Exception ex) {
            out.print("Error:" + ex.getMessage());
        } finally {
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
            out.close();
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
