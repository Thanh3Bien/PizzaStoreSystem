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
public class CartItem {
    private String itemID;
    private String itemName;
    private String itemDescription;
    private String itemSupplierID;
    private String itemCategoryID;
    private int itemQuantityPerUnit;
    private float itemPrice;
    private String itemImage;

    public CartItem() {
    }

    public CartItem(String itemID, String itemName, String itemDescription, String itemSupplierID, String itemCategoryID, int itemQuantityPerUnit, float itemPrice, String itemImage) {
        this.itemID = itemID;
        this.itemName = itemName;
        this.itemDescription = itemDescription;
        this.itemSupplierID = itemSupplierID;
        this.itemCategoryID = itemCategoryID;
        this.itemQuantityPerUnit = itemQuantityPerUnit;
        this.itemPrice = itemPrice;
        this.itemImage = itemImage;
    }

    public String getItemID() {
        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public String getItemSupplierID() {
        return itemSupplierID;
    }

    public void setItemSupplierID(String itemSupplierID) {
        this.itemSupplierID = itemSupplierID;
    }

    public String getItemCategoryID() {
        return itemCategoryID;
    }

    public void setItemCategoryID(String itemCategoryID) {
        this.itemCategoryID = itemCategoryID;
    }

    public int getItemQuantityPerUnit() {
        return itemQuantityPerUnit;
    }

    public void setItemQuantityPerUnit(int itemQuantityPerUnit) {
        this.itemQuantityPerUnit = itemQuantityPerUnit;
    }

    public float getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(float itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getItemImage() {
        return itemImage;
    }

    public void setItemImage(String itemImage) {
        this.itemImage = itemImage;
    }
    
    public double getSubTotal(){
        return itemQuantityPerUnit*itemPrice;
    }
    @Override
    public String toString(){
        return String.format("%s, %s, %s, %s, %s, %d, %f, %s", itemID, itemName, itemDescription, itemSupplierID, itemCategoryID, itemQuantityPerUnit, itemPrice, itemImage);
    }
    
}
