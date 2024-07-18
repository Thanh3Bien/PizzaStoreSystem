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

/**
 *
 * @author ADMIN
 */
public class AccountDAO {
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
    
    public Account login(String userName, String password) throws Exception{
        Account account = null;
        Connection cnn = null;
        PreparedStatement preStm = null;
        ResultSet rs = null;
        String accountID, fullName;
        boolean type;
        
        try{
            cnn = getConnection();
            String sql = "select AccountID, FullName, Type from Account where [UserName]=? and [Password]=?";
            preStm = cnn.prepareStatement(sql);
            preStm.setString(1, userName);
            preStm.setString(2, password);
            rs = preStm.executeQuery();
            while(rs.next()){
                accountID = rs.getString(1);
                fullName = rs.getString(2);
                type = rs.getBoolean(3);
                account = new Account(accountID, userName, password, fullName, type);
                
            }
        }
        catch (Exception ex){
            throw ex;
        }
        finally{
            if(rs!=null){
                rs.close();
            }
            if(preStm != null){
                preStm.close();
            }
            if(cnn != null){
                cnn.close();
            }
        }
        return account;
    }
    
}
