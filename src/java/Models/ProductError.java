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
public class ProductError {
    private String productIdError;
    private String productNameError;
    private String descriptionError;
    private String supplierIdError;
    private String categoryIdError;
    private String quantityPerUnitError;
    private String unitPriceError;
    private String productImageError;
    private String duplicateProductError;
    public ProductError() {
    }

    public ProductError(String productIdError, String productNameError, String descriptionError, String supplierIdError, String categoryIdError, String quantityPerUnitError, String unitPriceError, String productImageError, String duplicateProductError) {
        this.productIdError = productIdError;
        this.productNameError = productNameError;
        this.descriptionError = descriptionError;
        this.supplierIdError = supplierIdError;
        this.categoryIdError = categoryIdError;
        this.quantityPerUnitError = quantityPerUnitError;
        this.unitPriceError = unitPriceError;
        this.productImageError = productImageError;
        this.duplicateProductError = duplicateProductError;
    }

    

    public String getProductIdError() {
        return productIdError;
    }

    public void setProductIdError(String productIdError) {
        this.productIdError = productIdError;
    }

    public String getProductNameError() {
        return productNameError;
    }

    public void setProductNameError(String productNameError) {
        this.productNameError = productNameError;
    }

    public String getDescriptionError() {
        return descriptionError;
    }

    public void setDescriptionError(String descriptionError) {
        this.descriptionError = descriptionError;
    }

    public String getSupplierIdError() {
        return supplierIdError;
    }

    public void setSupplierIdError(String supplierIdError) {
        this.supplierIdError = supplierIdError;
    }

    public String getCategoryIdError() {
        return categoryIdError;
    }

    public void setCategoryIdError(String categoryIdError) {
        this.categoryIdError = categoryIdError;
    }

    public String getQuantityPerUnitError() {
        return quantityPerUnitError;
    }

    public void setQuantityPerUnitError(String quantityPerUnitError) {
        this.quantityPerUnitError = quantityPerUnitError;
    }

    public String getUnitPriceError() {
        return unitPriceError;
    }

    public void setUnitPriceError(String unitPriceError) {
        this.unitPriceError = unitPriceError;
    }

    public String getProductImageError() {
        return productImageError;
    }

    public void setProductImageError(String productImageError) {
        this.productImageError = productImageError;
    }

    public String getDuplicateProductError() {
        return duplicateProductError;
    }

    public void setDuplicateProductError(String duplicateProductError) {
        this.duplicateProductError = duplicateProductError;
    }
    
           
}
