

import Models.ProductDAO;
import Models.Product;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "UpdateProductServlet", urlPatterns = {"/UpdateProductServlet"})
public class UpdateProductServlet extends HttpServlet {

    private final String displayMessagePage = "DisplayMessage.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String productId, productName, description, supplierId, categoryId, productImage; 
        int quantityPerUnit;float unitPrice;

        String message = "Something went wrong";
        String url = displayMessagePage;
        PrintWriter out = response.getWriter();
        try {
            productId = request.getParameter("productId");
            productName = request.getParameter("productName");
            description = request.getParameter("description");
            supplierId = request.getParameter("supplierId");
            categoryId = request.getParameter("categoryId");
            quantityPerUnit = Integer.parseInt(request.getParameter("quantityPerUnit"));
            unitPrice = Float.parseFloat(request.getParameter("unitPrice"));
            productImage = request.getParameter("productImage");

            if (productId != null) {
                Product product = new Product(productId, productName, description, supplierId, categoryId, quantityPerUnit, unitPrice, productImage);
                ProductDAO productDao = new ProductDAO();
                if (productDao.updateProduct(product)) {
                    message = "The product " + productId + " has been updated successfully.";
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            request.setAttribute("action", "Update product");
            request.setAttribute("page", "login-success.jsp");
            request.setAttribute("message", message);
            RequestDispatcher rd = request.getRequestDispatcher(url);
            rd.forward(request, response);
            out.close();
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }
}