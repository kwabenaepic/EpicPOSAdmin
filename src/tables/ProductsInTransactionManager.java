package tables;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import database.ConnectionManager;
import beans.ProductsInTransaction;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ProductsInTransactionManager {

    private static ObservableList<ProductsInTransaction> product;
    private static Connection conn = ConnectionManager.getInstance().getConnection();

    public static ObservableList<ProductsInTransaction> getProductsList(String ticketNumber) throws SQLException {
        ObservableList<ProductsInTransaction> productList = FXCollections.observableArrayList();
        String sql = "SELECT * FROM productsbyticketnumber WHERE ticketNumber = ?";
        ResultSet rs = null;

        try (
                PreparedStatement stmt = conn.prepareStatement(sql);) {
//            ResultSet rs = stmt.executeQuery();
            stmt.setString(1, ticketNumber);
            rs = stmt.executeQuery();

            while (rs.next()) {
                ProductsInTransaction bean = new ProductsInTransaction();
                bean.setProductId(rs.getInt("productId"));
                bean.setProductName(rs.getString("productName"));
                bean.setProductDescription(rs.getString("productDescription"));
                bean.setQuantityBought(rs.getInt("quantityBought"));
                bean.setTicketNumber(rs.getString("ticketNumber"));
                bean.setUnitPrice(rs.getDouble("unitPrice"));
                bean.setTotal(rs.getDouble("total"));

                productList.add(bean);
            }
            return productList;

        }
    }

    public static ObservableList<ProductsInTransaction> getBestSelling() throws SQLException {
        ObservableList<ProductsInTransaction> productList = FXCollections.observableArrayList();
        String sql = "SELECT * FROM productsbyticketnumber  ORDER by quantityBought DESC";
//                + "WHERE ticketNumber = ?";
        ResultSet rs = null;

        try (
                PreparedStatement stmt = conn.prepareStatement(sql);) {
//            ResultSet rs = stmt.executeQuery();
//            stmt.setString(1, ticketNumber);
            rs = stmt.executeQuery();

            while (rs.next()) {
                ProductsInTransaction bean = new ProductsInTransaction();
                bean.setProductId(rs.getInt("productId"));
                bean.setProductName(rs.getString("productName"));
                bean.setProductDescription(rs.getString("productDescription"));
                bean.setQuantityBought(rs.getInt("quantityBought"));
                bean.setTicketNumber(rs.getString("ticketNumber"));
                bean.setUnitPrice(rs.getDouble("unitPrice"));
                bean.setTotal(rs.getDouble("total"));

                productList.add(bean);

            }
            return productList;

        }
    }

    public static ObservableList<ProductsInTransaction> getSaleportBetweenDateRange(String dateOne, String dateTwo) throws SQLException {
        ObservableList<ProductsInTransaction> productList = FXCollections.observableArrayList();
        String sql = "SELECT * FROM salereports "
                + "WHERE DATE(receiptDate)"
                + "BETWEEN ? AND ?"
                + "ORDER BY receiptDate DESC ";
        ResultSet rs = null;
        try (
                PreparedStatement stmt = conn.prepareStatement(sql);) {
            stmt.setString(1, dateOne);
            stmt.setString(2, dateTwo);
            rs = stmt.executeQuery();
            while (rs.next()) {
                ProductsInTransaction bean = new ProductsInTransaction();
                bean.setProductId(rs.getInt("productId"));
                bean.setProductName(rs.getString("productName"));
                bean.setProductDescription(rs.getString("productDescription"));
                bean.setQuantityBought(rs.getInt("quantityBought"));
                bean.setTicketNumber(rs.getString("ticketNumber"));
                bean.setUnitPrice(rs.getDouble("unitPrice"));
                bean.setTotal(rs.getDouble("total"));
                productList.add(bean);

            }
            return productList;
        }
    }

}
