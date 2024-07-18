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
public class ProductDAO {

    public Connection getConnection() throws Exception {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String connectionString = "jdbc:sqlserver://localhost:1433;database=PizzaStore";
            Connection cnn = DriverManager.getConnection(connectionString, "sa", "1");
            return cnn;
        } catch (ClassNotFoundException | SQLException ex) {
            throw ex;
        }
    }

    public List<Product> getProductList() throws Exception {
        String productId, productName, description, supplierId, categoryId, productImage;
        int quantityPerUnit;
        float unitPrice;
        Connection cnn = null;
        PreparedStatement preStm = null;
        List<Product> productList = new ArrayList();
        ResultSet rs = null;
        try {
            if (cnn == null || cnn.isClosed()) {
                cnn = getConnection();
            }
            String sql = "select * from Products";
            preStm = cnn.prepareStatement(sql);
            rs = preStm.executeQuery();
            while (rs.next()) {
                productId = rs.getString(1);
                productName = rs.getString(2);
                description = rs.getString(3);
                supplierId = rs.getString(4);
                categoryId = rs.getString(5);
                quantityPerUnit = rs.getInt(6);
                unitPrice = rs.getFloat(7);
                productImage = rs.getString(8);

                Product product = new Product(productId, productName, description, supplierId, categoryId, quantityPerUnit, unitPrice, productImage);
                productList.add(product);
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

        return productList;
    }

//        public List<Product> searchMobile(String searchMobile) throws Exception {
//            Connection cnn = null;
//            PreparedStatement pr = null;
//            String mobileId, description, mobileName;
//            float price;
//            int yearOfProduction, quantity;
//            boolean notSale;
//            List<Product> mobileList = new ArrayList<>();
//            ResultSet rs = null;
//            try {
//                cnn = getConnection();
//                String SQL = "select mobileId, description, price, mobileName, yearOfProduction, quantity, notSale FROM Mobiles WHERE  mobileName like ?";
//                pr = cnn.prepareStatement(SQL);
//                pr.setString(1, "%" + searchMobile + "%");
//                rs = pr.executeQuery();
//                while (rs.next()) {
//                    mobileId = rs.getString(1);
//                    description = rs.getString(2);
//                    price = rs.getInt(3);
//                    mobileName = rs.getString(4);
//                    yearOfProduction = rs.getInt(5);
//                    quantity = rs.getInt(6);
//                    notSale = rs.getBoolean(7);
//                    Mobiles mobile = new Mobiles(mobileId, description, price, mobileName, yearOfProduction, quantity, notSale);
//                    mobileList.add(mobile);
//                }
//            } catch (Exception e) {
//                throw e;
//            } finally {
//                if (cnn != null) {
//                    cnn.close();
//                }
//                if (pr != null) {
//                    pr.close();
//                }
//                if (rs != null) {
//                    rs.close();
//                }
//
//            }
//            return mobileList;
//        }
    public Product getProductById(String productId) throws Exception {
        Connection cnn = null;
        PreparedStatement pr = null;
        String productName, description, supplierId, categoryId, productImage;
        int quantityPerUnit;
        float unitPrice;
        ResultSet rs = null;
        Product product = null;
        try {
            cnn = getConnection();
            String SQL = "select * FROM Products WHERE ProductID =?";
            pr = cnn.prepareStatement(SQL);
            pr.setString(1, productId);

            rs = pr.executeQuery();
            while (rs.next()) {
                productName = rs.getString(2);
                description = rs.getString(3);
                supplierId = rs.getString(4);
                categoryId = rs.getString(5);
                quantityPerUnit = rs.getInt(6);
                unitPrice = rs.getFloat(7);
                productImage = rs.getString(8);
                product = new Product(productId, productName, description, supplierId, categoryId, quantityPerUnit, unitPrice, productImage);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (cnn != null) {
                cnn.close();
            }
            if (pr != null) {
                pr.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return product;
    }
//

    public boolean DeleteMobile(String mobileId) throws Exception {
        Connection cnn = null;
        PreparedStatement pr = null;

        try {
            cnn = getConnection();
            String SQL = "DELETE Mobiles  WHERE mobileId = ?";
            pr = cnn.prepareStatement(SQL);

            pr.setString(1, mobileId);

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
//
//    
//
//    public List<Mobiles> searchMobileByPrice(float searchMinPrice, float searchMaxPrice) throws Exception {
//        Connection cnn = null;
//        PreparedStatement pr = null;
//        String mobileId, description, mobileName;
//        float price;
//        int yearOfProduction, quantity;
//        boolean notSale;
//        List<Mobiles> mobileList = new ArrayList<>();
//        ResultSet rs = null;
//        try {
//            cnn = getConnection();
//            String SQL = "select mobileId, description, price, mobileName, yearOfProduction, quantity, notSale FROM Mobiles WHERE  price >= ? AND price <= ?";
//            pr = cnn.prepareStatement(SQL);
//            pr.setFloat(1, searchMinPrice);
//            pr.setFloat(2, searchMaxPrice);
//            rs = pr.executeQuery();
//            while (rs.next()) {
//                mobileId = rs.getString(1);
//                description = rs.getString(2);
//                price = rs.getInt(3);
//                mobileName = rs.getString(4);
//                yearOfProduction = rs.getInt(5);
//                quantity = rs.getInt(6);
//                notSale = rs.getBoolean(7);
//                Mobiles mobile = new Mobiles(mobileId, description, price, mobileName, yearOfProduction, quantity, notSale);
//                mobileList.add(mobile);
//            }
//        } catch (Exception e) {
//            throw e;
//        } finally {
//            if (cnn != null) {
//                cnn.close();
//            }
//            if (pr != null) {
//                pr.close();
//            }
//            if (rs != null) {
//                rs.close();
//            }
//
//        }
//        return mobileList;
//    }
//    
//    public void updateMobileQuantity(String mobileId, int newQuantity) throws SQLException, Exception {
//    Connection cnn = null;
//    PreparedStatement pr = null;
//    ResultSet rs = null;
//    try {
//        cnn = getConnection(); // Hàm getConnection() trả về kết nối tới cơ sở dữ liệu
//
//        String sql = "UPDATE Mobiles SET quantity = ? WHERE mobileId = ?";
//        pr = cnn.prepareStatement(sql);
//        pr.setInt(1, newQuantity);
//        pr.setString(2, mobileId);
//
//        pr.executeUpdate();
//    } finally {
//        // Đóng tài nguyên
//        if (cnn != null) {
//                cnn.close();
//            }
//            if (pr != null) {
//                pr.close();
//            }
//            if (rs != null) {
//                rs.close();
//            }
//    }
//}

    public boolean AddProduct(Product product) throws Exception {
        Connection cnn = null;
        PreparedStatement pr = null;

        try {
            cnn = getConnection();
            String SQL = "INSERT Products values(?,?,?,?,?,?,?,?)";
            pr = cnn.prepareStatement(SQL);
            pr.setString(1, product.getProductID());
            pr.setString(2, product.getProductName());
            pr.setString(3, product.getDescription());
            pr.setString(4, product.getSupplierID());
            pr.setString(5, product.getCategoryID());
            pr.setInt(6, product.getQuantityPerUnit());
            pr.setFloat(7, product.getUnitPrice());
            pr.setString(8, product.getProductImage());
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

    public boolean DeleteProduct(String productId) throws Exception {
        Connection cnn = null;
        PreparedStatement pr = null;

        try {
            cnn = getConnection();
            String SQL = "DELETE Products  WHERE ProductID = ?";
            pr = cnn.prepareStatement(SQL);

            pr.setString(1, productId);

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

    public boolean updateProduct(Product product) throws Exception {
        Connection cnn = null;
        PreparedStatement pr = null;

        try {
            cnn = getConnection();
            String SQL = "Update Products SET ProductName = ?, Description =?, SupplierID = ?, CategoryID = ?, QuantityPerUnit = ?, UnitPrice=?, ProductImage=? WHERE ProductID = ?";
            pr = cnn.prepareStatement(SQL);

            pr.setString(1, product.getProductName());
            pr.setString(2, product.getDescription());
            pr.setString(3, product.getSupplierID());
            pr.setString(4, product.getCategoryID());
            pr.setInt(5, product.getQuantityPerUnit());
            pr.setFloat(6, product.getUnitPrice());
            pr.setString(7, product.getProductImage());
            pr.setString(8, product.getProductID());
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

    public List<Product> searchProductByFullName(String searchValue) throws Exception {
        Connection cnn = null;
        PreparedStatement pr = null;
        String ProductID, ProductName, Description, SupplierID, CategoryID, ProductImage;
        int QuantityPerUnit;
        float UnitPrice;
        List<Product> productList = new ArrayList<>();
        ResultSet rs = null;
        try {
            cnn = getConnection();
            String SQL = "SELECT ProductID, ProductName, Description, SupplierID, CategoryID, QuantityPerUnit, UnitPrice, ProductImage FROM Products WHERE ProductName LIKE ?";
            pr = cnn.prepareStatement(SQL);
            pr.setString(1, "%" + searchValue + "%");
            rs = pr.executeQuery();
            while (rs.next()) {
                ProductID = rs.getString(1);
                ProductName = rs.getString(2);
                Description = rs.getString(3);
                SupplierID = rs.getString(4);
                CategoryID = rs.getString(5);
                QuantityPerUnit = rs.getInt(6);
                UnitPrice = rs.getFloat(7);
                ProductImage = rs.getString(8);
                Product product = new Product(ProductID, ProductName, Description, SupplierID, CategoryID, QuantityPerUnit, UnitPrice, ProductImage);
                productList.add(product);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (cnn != null) {
                cnn.close();
            }
            if (pr != null) {
                pr.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return productList;
    }

    public String getCagoryNameByID(String categoryID) throws Exception {
        String categoryName = null;
        Connection cnn = null;
        PreparedStatement pr = null;
        ResultSet rs = null;
        try {
            cnn = getConnection();
            String query = "SELECT CategoryName FROM Categories WHERE CategoryID = ?";
            pr = cnn.prepareStatement(query);
            pr.setString(1, categoryID);
            rs = pr.executeQuery();
            while (rs.next()) {
                categoryName = rs.getString(1);
            }
        } catch (Exception ex) {
            throw ex;
        } finally {
            if (cnn != null) {
                cnn.close();
            }
            if (pr != null) {
                pr.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return categoryName;
    }
    
    public boolean checkProductByID(String productID) throws SQLException, Exception{
        Connection cnn = null;
        PreparedStatement pr = null;
        ResultSet rs = null;
        boolean check = false;
        try{
            cnn = getConnection();
            String SQL = "SELECT Count(*) FROM Products WHERE ProductID = ?";
            pr = cnn.prepareStatement(SQL);
            pr.setString(1, productID);
            rs = pr.executeQuery();
            if(rs.next()){
                int count = rs.getInt(1);
                check = count > 0;
            }
        }
        catch(Exception ex){
            throw ex;
        }
        finally{
            if (cnn != null) {
                cnn.close();
            }
            if (pr != null) {
                pr.close();
            }
            if (rs != null) {
                rs.close();
            }
        }
        return check;
    }

}
