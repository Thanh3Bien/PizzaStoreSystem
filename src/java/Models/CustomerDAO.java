/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class CustomerDAO {

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

    public List<Customer> getAllCustomers() throws Exception {
        List<Customer> customers = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = getConnection();
            String query = "SELECT * FROM Customers";
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();

            while (resultSet.next()) {
                String customerID = resultSet.getString(1);
                String password = resultSet.getString(2);
                String contactName = resultSet.getString(3);
                String address = resultSet.getString(4);
                String phone = resultSet.getString(5);
                Customer customer = new Customer(customerID, password, contactName, address, phone);
                customers.add(customer);
            }
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }

        return customers;
    }

    public Customer login(String customerID, String password) throws Exception {
        Customer customer = null;
        Connection cnn = null;
        PreparedStatement preStm = null;
        ResultSet rs = null;
        String contactName, address, phone;

        try {
            cnn = getConnection();
            String sql = "select ContactName, Address, Phone from Customers where [CustomerID]=? and [Password]=?";
            preStm = cnn.prepareStatement(sql);
            preStm.setString(1, customerID);
            preStm.setString(2, password);
            rs = preStm.executeQuery();
            while (rs.next()) {
                contactName = rs.getString(1);
                address = rs.getString(2);
                phone = rs.getString(3);
                customer = new Customer(customerID, password, contactName, address, phone);

            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (preStm != null) {
                preStm.close();
            }
            if (cnn != null) {
                cnn.close();
            }
        }
        return customer;
    }

    public boolean addCustomer(Customer customer) throws Exception {
        Connection cnn = null;
        PreparedStatement pr = null;
        ResultSet rs = null;
        try {
            cnn = getConnection();
            String SQL = "Insert Customers values(?,?,?,?,?)";
            pr = cnn.prepareStatement(SQL);
            pr.setString(1, customer.getCustomerID());
            pr.setString(2, customer.getPassword());
            pr.setString(3, customer.getContactName());
            pr.setString(4, customer.getAddress());
            pr.setString(5, customer.getPhone());
            return pr.executeUpdate() > 0;

        } catch (Exception ex) {
            throw ex;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pr != null) {
                pr.close();
            }
            if (cnn != null) {
                cnn.close();
            }
        }
    }

//    public boolean checkCustomerByID(String customerID) throws Exception {
//        Connection cnn = null;
//        PreparedStatement pr = null;
//        ResultSet rs = null;
//        String checkID = "";
//        boolean check;
//        try {
//            cnn = getConnection();
//            String SQL = "SELECT CustomerID From Customer WHERE CustomerID = ?";
//            pr = cnn.prepareStatement(SQL);
//            pr.setString(1, customerID);
//            rs = pr.executeQuery();
//            while (rs.next()) {
//                checkID = rs.getString(1);
//            }
//            if (checkID.equals(customerID)) {
//                check = true;
//            } else {
//                check = false;
//            }
//        } catch (Exception ex) {
//            throw ex;
//        } finally {
//            if (rs != null) {
//                rs.close();
//            }
//            if (pr != null) {
//                pr.close();
//            }
//            if (cnn != null) {
//                cnn.close();
//            }
//        }
//        return check;
//    }
    public boolean checkCustomerByID(String customerID) throws Exception {
        Connection cnn = null;
        PreparedStatement pr = null;
        ResultSet rs = null;
        boolean check = false;

        try {
            cnn = getConnection();
            String SQL = "SELECT COUNT(*) FROM Customers WHERE CustomerID = ?";
            pr = cnn.prepareStatement(SQL);
            pr.setString(1, customerID);
            rs = pr.executeQuery();

            if (rs.next()) {
                int count = rs.getInt(1);
                check = count > 0;
            }
        } catch (Exception ex) {

            throw ex;
        } finally {

            if (rs != null) {
                rs.close();
            }
            if (pr != null) {
                pr.close();
            }
            if (cnn != null) {
                cnn.close();
            }
        }

        return check;
    }
}
