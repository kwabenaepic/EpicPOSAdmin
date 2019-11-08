package tables;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import database.ConnectionManager;
import beans.Product;
import java.sql.PreparedStatement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ProductManager {

    private static ObservableList<Product> product;

    private static Connection conn = ConnectionManager.getInstance().getConnection();

//    public static void getAllProducts() throws SQLException {
//
//        String sql = "SELECT * FROM Products";
//        try (
//                Statement stmt = conn.createStatement();
//                ResultSet rs = stmt.executeQuery(sql);) {
//
//            System.out.println("Product Table:");
//            while (rs.next()) {
//                StringBuilder bf = new StringBuilder();
//                bf.append(rs.getInt("id")).append(": ");
//                bf.append(rs.getString("productId")).append(": ");
//                bf.append(rs.getString("description")).append(", ");
//
//                bf.append(rs.getDouble("price")).append(", ");
//                bf.append(rs.getInt("quantity")).append(", ");
//
////                product.add(new Product(rs.getInt("id"), rs.getString("description"), rs.getDouble("price"), rs.getInt("quantity")));
//                System.out.println(bf.toString());
//
//            }
//        }
//    }
    public static ObservableList<Product> getProductsList() throws SQLException {
        ObservableList<Product> productList = FXCollections.observableArrayList();
        String sql = "SELECT * FROM product";
        try (
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql);) {

            System.out.println("Product Table:");
            while (rs.next()) {
                Product bean = new Product();
                bean.setCategory(rs.getString("category"));
                bean.setCostPrice(rs.getDouble("costPrice"));
                bean.setDescription(rs.getString("description"));
                bean.setProductId(rs.getInt("productId"));
//                bean.setProductNumber(rs.getString("productNumber"));
                bean.setName(rs.getString("name"));
                bean.setQuantity(rs.getInt("quantity"));
                bean.setReorderLevel(rs.getInt("reorderLevel"));
//                bean.setSuppleirId(rs.getString("suppleirId"));
                bean.setUnitPrice(rs.getDouble("unitPrice"));
//                bean.setAttribute(rs.getString("attribute"));
//                bean.setSize(rs.getString("size"));
                bean.setSKU(rs.getString("SKU"));
                bean.setUPC(rs.getString("UPC"));
                bean.setVendorCode(rs.getString("vendorCode"));

                productList.add(bean);

            }
            return productList;

        }
    }

    public static ObservableList<Product> getReorderList() throws SQLException {
        ObservableList<Product> reOrderList = FXCollections.observableArrayList();
        String sql = "Select * from product where quantity <= reorderLevel";
        try (
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql);) {

            while (rs.next()) {
                Product bean = new Product();
                bean.setProductId(rs.getInt("productId"));
                bean.setName(rs.getString("name"));
                bean.setQuantity(rs.getInt("quantity"));
                reOrderList.add(bean);
            }
            return reOrderList;
        }
    }

    public static boolean insert(beans.Product bean) throws Exception {

        String sql = "INSERT into product (name, SKU, UPC, vendorCode, description, "
             + "costPrice, unitPrice, quantity, reorderLevel) "
             + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        ResultSet keys = null;
        try (
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {

            stmt.setString(1, bean.getName());
//            stmt.setString(2, bean.getCategory());
//            stmt.setString(3, bean.getSuppleirId());
//            stmt.setString(3, bean.getAttribute());
//            stmt.setString(4, bean.getSize());
            stmt.setString(2, bean.getSKU());
            stmt.setString(3, bean.getUPC());
            stmt.setString(4, bean.getVendorCode());
            stmt.setString(5, bean.getDescription());
            stmt.setDouble(6, bean.getCostPrice());
            stmt.setDouble(7, bean.getUnitPrice());
            stmt.setInt(8, bean.getQuantity());
            stmt.setInt(9, bean.getReorderLevel());

            int affected = stmt.executeUpdate();

            if (affected == 1) {
                keys = stmt.getGeneratedKeys();
                keys.next();
                int newKey = keys.getInt(1);
                bean.setProductId(newKey);
            } else {
                System.err.println("No rows affected");
                return false;
            }

        } catch (SQLException e) {
            System.err.println(e);
            return false;
        } finally {
            if (keys != null) {
                keys.close();
            }
        }
        return true;
    }

//    public static Product getRow(int id) throws SQLException {
//
//        String sql = "SELECT * FROM Product WHERE id = ?";
//        ResultSet rs = null;
//
//        try (
//                PreparedStatement stmt = conn.prepareStatement(sql);) {
//            stmt.setInt(1, id);
//            rs = stmt.executeQuery();
//
//            if (rs.next()) {
//                Product bean = new Product();
//                bean.setId(id);
//                bean.setUserName(rs.getString("userName"));
//                bean.setPassword(rs.getString("password"));
//
//                return bean;
//
//            } else {
//                return null;
//            }
//
//        } catch (SQLException e) {
//            System.err.println(e);
//            return null;
//        } finally {
//            if (rs != null) {
//                rs.close();
//            }
//        }
//
//    }
//    public static Product validate(String username, String password) throws SQLException {
//
//        String sql = "SELECT * FROM Products WHERE userName = ? AND password = ?";
//        ResultSet rs = null;
//        try (
//                PreparedStatement stmt = conn.prepareStatement(sql);) {
//            stmt.setString(1, username);
//            stmt.setString(2, password);
//            rs = stmt.executeQuery();
//
//            if (rs.next()) {
//                Product bean = new Product();
//
////                bean.setUserName(rs.getString("userName"));
////                bean.setPassword(rs.getString("password"));
//                return bean;
//            } else {
//                return null;
//            }
//
//        } catch (SQLException e) {
//            System.err.println(e);
//            return null;
//        } finally {
//            if (rs != null) {
//                rs.close();
//            }
//        }
//}
//    public static boolean insert(Product bean) throws Exception {
//
//        String sql = "INSERT into Product (userName, password) "
//                + "VALUES (?, ?)";
//        ResultSet keys = null;
//        try (
//                PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
//
//            stmt.setString(1, bean.getUserName());
//            stmt.setString(2, bean.getPassword());
//            int affected = stmt.executeUpdate();
//
//            if (affected == 1) {
//                keys = stmt.getGeneratedKeys();
//                keys.next();
//                int newKey = keys.getInt(1);
//                bean.setId(newKey);
//            } else {
//                System.err.println("No rows affected");
//                return false;
//            }
//
//        } catch (SQLException e) {
//            System.err.println(e);
//            return false;
//        } finally {
//            if (keys != null) {
//                keys.close();
//            }
//        }
//        return true;
//    }
//    public static boolean update(Product bean) throws Exception {
//
//        String sql
//                = "UPDATE Product SET "
//                + "userName = ?, password = ? "
//                + "WHERE productsId = ?";
//        try (
//                PreparedStatement stmt = conn.prepareStatement(sql);) {
//
//            stmt.setString(1, bean.getUserName());
//            stmt.setString(2, bean.getPassword());
//            stmt.setInt(3, bean.getId());
//
//            int affected = stmt.executeUpdate();
//            if (affected == 1) {
//                return true;
//            } else {
//                return false;
//            }
//
//        } catch (SQLException e) {
//            System.err.println(e);
//            return false;
//        }
//
//    }
}
