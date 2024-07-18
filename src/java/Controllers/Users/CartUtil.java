/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers.Users;

import Models.CartItem;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CartUtil {
    public Cookie getCookieByName(HttpServletRequest request, String cookieName) {
        Cookie[] arrCookies = request.getCookies();
        if (arrCookies != null) {
            for (Cookie cookie : arrCookies) {
                if (cookie.getName().equals(cookieName)) {
                    return cookie;
                }
            }
        }
        return null;
    }

    public HashMap<String, CartItem> getCartFromCookie(Cookie cookieCart) {

        HashMap<String, CartItem> cart = null;
        String[] arrItemDetail;
        String itemId, itemName, itemDescription, itemSupplierID, itemCategoryID, itemImage;
        int itemQuantityPerUnit;
        float itemPrice;
        CartItem item;
        Base64.Decoder base64Decoder = Base64.getDecoder();
        cart = new HashMap<>();
        String encodedString = new String(base64Decoder.decode(cookieCart.getValue().getBytes()));
        String[] itemList = encodedString.split("\\|");
        for (String strItem : itemList) {
            arrItemDetail = strItem.split(",");
            itemId = arrItemDetail[0].trim();
            itemName = arrItemDetail[1].trim();
            itemDescription = arrItemDetail[2].trim();
            itemSupplierID = arrItemDetail[3].trim();
            itemCategoryID = arrItemDetail[4].trim();
            itemQuantityPerUnit = Integer.parseInt(arrItemDetail[5].trim());
            itemPrice = Float.parseFloat(arrItemDetail[6].trim());
            itemImage = arrItemDetail[7].trim();
            item = new CartItem(itemId, itemName, itemDescription, itemSupplierID, itemCategoryID, itemQuantityPerUnit, itemPrice, itemImage);
            cart.put(itemId, item);
        }
        return cart;
    }

    public void saveCartToCookie(HttpServletRequest request, HttpServletResponse response, String strItemsInCart) {
        String cookieName = "Cart";
        Cookie cookieCart = getCookieByName(request, cookieName);
        if (cookieCart != null) {
            cookieCart.setValue(strItemsInCart);
        } else {
            cookieCart = new Cookie(cookieName, strItemsInCart);
        }
        cookieCart.setMaxAge(30);
        response.addCookie(cookieCart);
    }

    public String convertCartToString(List<CartItem> itemsList) {
        StringBuilder strItemsInCart = new StringBuilder();
        for (CartItem item : itemsList) {
            // Convert each item to the desired format: itemId, itemName, itemDescription, itemSupplierID, itemCategoryID, itemQuantityPerUnit, itemPrice, itemImage
            String itemString = item.getItemID() + "," +
                    item.getItemName() + "," +
                    item.getItemDescription() + "," +
                    item.getItemSupplierID() + "," +
                    item.getItemCategoryID() + "," +
                    item.getItemQuantityPerUnit() + "," +
                    item.getItemPrice() + "," +
                    item.getItemImage();
            strItemsInCart.append(itemString).append("|");
        }
        
        Base64.Encoder base64Encoder = Base64.getEncoder();
        String encodedString = base64Encoder.encodeToString(strItemsInCart.toString().getBytes());
        return encodedString;
    }
}
