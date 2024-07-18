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
public class CategoryDAO {
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
    public List<Category> getAllCategories() throws Exception {
        List<Category> categories = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        
        try {
            connection = getConnection();
            String query = "SELECT * FROM Categories";
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            
            while (resultSet.next()) {
                String categoryID = resultSet.getString(1);
                String categoryName = resultSet.getString(2);
                String description = resultSet.getString(3);
                
                Category category = new Category(categoryID, categoryName, description);
                categories.add(category);
            }
        } finally {
            if (resultSet != null) resultSet.close();
            if (statement != null) statement.close();
            if (connection != null) connection.close();
        }
        
        return categories;
    }
}

