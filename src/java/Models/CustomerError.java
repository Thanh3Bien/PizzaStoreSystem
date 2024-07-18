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
public class CustomerError {
    private String customerIdError;
    private String passwordError;
    private String contactNameError;
    private String addressError;
    private String phoneError;
    private String duplicateCustomerError;
    public CustomerError() {
    }

    public CustomerError(String customerIdError, String passwordError, String contactNameError, String addressError, String phoneError, String duplicateCustomerError) {
        this.customerIdError = customerIdError;
        this.passwordError = passwordError;
        this.contactNameError = contactNameError;
        this.addressError = addressError;
        this.phoneError = phoneError;
        this.duplicateCustomerError = duplicateCustomerError;
    }

    

    public String getCustomerIdError() {
        return customerIdError;
    }

    public void setCustomerIdError(String customerIdError) {
        this.customerIdError = customerIdError;
    }

    public String getPasswordError() {
        return passwordError;
    }

    public void setPasswordError(String passwordError) {
        this.passwordError = passwordError;
    }

    public String getContactNameError() {
        return contactNameError;
    }

    public void setContactNameError(String contactNameError) {
        this.contactNameError = contactNameError;
    }

    public String getAddressError() {
        return addressError;
    }

    public void setAddressError(String addressError) {
        this.addressError = addressError;
    }

    public String getPhoneError() {
        return phoneError;
    }

    public void setPhoneError(String phoneError) {
        this.phoneError = phoneError;
    }

    public String getDuplicateCustomerError() {
        return duplicateCustomerError;
    }

    public void setDuplicateCustomerError(String duplicateCustomerError) {
        this.duplicateCustomerError = duplicateCustomerError;
    }
    
}
