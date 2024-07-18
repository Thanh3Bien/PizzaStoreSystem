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
public class OrderError {
    private String orderIdError;
    private String customerIdError;
    private String orderDateError;
    private String requiredDateError;
    private String shippedDateError;
    private String frieghtError;
    private String shipAddressError;
    private String duplicateOrderError;
    private String logicError;
    public OrderError() {
    }

    public OrderError(String orderIdError, String customerIdError, String orderDateError, String requiredDateError, String shippedDateError, String frieghtError, String shipAddressError, String duplicateOrderError, String logicError) {
        this.orderIdError = orderIdError;
        this.customerIdError = customerIdError;
        this.orderDateError = orderDateError;
        this.requiredDateError = requiredDateError;
        this.shippedDateError = shippedDateError;
        this.frieghtError = frieghtError;
        this.shipAddressError = shipAddressError;
        this.duplicateOrderError = duplicateOrderError;
        this.logicError = logicError;
    }

    

    public String getOrderIdError() {
        return orderIdError;
    }

    public void setOrderIdError(String orderIdError) {
        this.orderIdError = orderIdError;
    }

    public String getCustomerIdError() {
        return customerIdError;
    }

    public void setCustomerIdError(String customerIdError) {
        this.customerIdError = customerIdError;
    }

    public String getOrderDateError() {
        return orderDateError;
    }

    public void setOrderDateError(String orderDateError) {
        this.orderDateError = orderDateError;
    }

    public String getRequiredDateError() {
        return requiredDateError;
    }

    public void setRequiredDateError(String requiredDateError) {
        this.requiredDateError = requiredDateError;
    }

    public String getShippedDateError() {
        return shippedDateError;
    }

    public void setShippedDateError(String shippedDateError) {
        this.shippedDateError = shippedDateError;
    }

    public String getFrieghtError() {
        return frieghtError;
    }

    public void setFrieghtError(String frieghtError) {
        this.frieghtError = frieghtError;
    }

    public String getShipAddressError() {
        return shipAddressError;
    }

    public void setShipAddressError(String shipAddressError) {
        this.shipAddressError = shipAddressError;
    }

    public String getDuplicateOrderError() {
        return duplicateOrderError;
    }

    public void setDuplicateOrderError(String duplicateOrderError) {
        this.duplicateOrderError = duplicateOrderError;
    }

    public String getLogicError() {
        return logicError;
    }

    public void setLogicError(String logicError) {
        this.logicError = logicError;
    }
    
    
}
