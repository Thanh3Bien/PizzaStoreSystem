/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author ADMIN
 */
public class OrderDetailDAO {
    public Connection getConnection() throws Exception {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String connectionString = "jdbc:sqlserver://localhost:1433;database=PizzaStore";
            Connection cnn = DriverManager.getConnection(connectionString, "sa", "12345");
            return cnn;
        } catch (ClassNotFoundException | SQLException ex) {
            throw ex;
        }

    }
    
    public boolean addDetail(OrderDetail orderDetail) throws Exception {
        Connection cnn = null;
        PreparedStatement pr = null;

        try {
            cnn = getConnection();
            String SQL = "INSERT OrderDetails values(?,?,?,?)";
            pr = cnn.prepareStatement(SQL);
            pr.setString(1, orderDetail.getOrderID());
            pr.setString(2, orderDetail.getProductID());
            pr.setFloat(3, orderDetail.getPrice());
            pr.setInt(4, orderDetail.getQuantity());
            
            return pr.executeUpdate() > 0;
        } catch (Exception e) {
            throw e;
        } finally {
            if (cnn != null) {
                cnn.close();
            }
            if (pr != null) {
                pr.close();
            }

        }
    }
}
