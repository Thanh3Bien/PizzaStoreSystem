/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

/**
 *
 * @author ADMIN
 */
public class Account {
    private String accountID;
    private String userName;
    private String password;
    private String fullName;
    private boolean type;

    public Account() {
    }

    public Account(String accountID, String userName, String password, String fullName, boolean type) {
        this.accountID = accountID;
        this.userName = userName;
        this.password = password;
        this.fullName = fullName;
        this.type = type;
    }

    public String getAccountID() {
        return accountID;
    }

    public void setAccountID(String accountID) {
        this.accountID = accountID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public boolean getType() {
        return type;
    }

    public void setType(boolean type) {
        this.type = type;
    }
    
    public Account validate() throws Exception{
        AccountDAO userDAO = new AccountDAO();
        Account account = userDAO.login(userName, password);
        if(account!=null){
            return account;
        }
        return null;
    }
    
    
}
