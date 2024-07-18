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
public class OrderDAO {
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
    public boolean AddOrder(Order order) throws Exception {
    Connection cnn = null;
    PreparedStatement pr = null;

    try {
        cnn = getConnection();
        String SQL = "INSERT INTO Orders (OrderID, CustomerID, OrderDate, RequiredDate, ShippedDate, Freight, ShipAddress) VALUES (?,?,?,?,?,?,?)";
        pr = cnn.prepareStatement(SQL);
        pr.setString(1, order.getOrderID());
        pr.setString(2, order.getCustomerID());
        pr.setString(3, order.getOrderDate());
        pr.setString(4, order.getRequiredDate());
        pr.setString(5, order.getShippedDate());
        pr.setBigDecimal(6, order.getFreight());
        pr.setString(7, order.getShipAddress());
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
