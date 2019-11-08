
/*
* To change this license header, choose License Headers in Project Properties.
* To change this template file, choose Tools | Templates
* and open the template in the editor.
 */
package tables;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import beans.SaleReports;
import beans.TopTenSelling;

import database.ConnectionManager;

/**
 *
 * @author KwabenaEpic
 */
public class SaleReportsManager {

    private static Connection conn = ConnectionManager.getInstance().getConnection();

    public static ObservableList<SaleReports> getAllSaleReport() throws SQLException {
        ObservableList<SaleReports> saleReportsList = FXCollections.observableArrayList();

//      String sql = "SELECT * FROM salereport WHERE dateRange = ? ORDER BY DATE(date) asc";.
        String sql = "SELECT * FROM salereports  ORDER BY receiptDate DESC";

        try (PreparedStatement stmt = conn.prepareStatement(sql);) {

//          stmt.setString(1, dateRange);
            ResultSet rs = stmt.executeQuery();

//          Statement stmt = conn.createStatement();
//          ResultSet rs = stmt.executeQuery(sql);) {
            System.out.println("SaleReports:");

            while (rs.next()) {
                SaleReports bean = new SaleReports();

                bean.setDate(rs.getString("receiptDate"));
                bean.setTicketNumber(rs.getString("ticketNumber"));
                bean.setSalesOutletId(rs.getString("salesOutletId"));
                bean.setReceiptId(rs.getInt("receiptsId"));
                bean.setNumberOfItems(rs.getInt("numberOfItems"));
                bean.setTotal(rs.getDouble("total"));
                bean.setModeOfPayment(rs.getString("modeOfPayment"));
                bean.setEmployeeLastName(rs.getString("lastName"));
                bean.setAmountPaid(rs.getDouble("amountPaid"));
                bean.setBalance(rs.getDouble("balance"));
                saleReportsList.add(bean);
            }

            return saleReportsList;
        }
    }

    public static ObservableList<SaleReports> getSaleportBetweenDateRange(String dateOne, String dateTwo)
         throws SQLException {
        ObservableList<SaleReports> saleReportsList = FXCollections.observableArrayList();
        String sql = "SELECT * FROM salereports " + "WHERE DATE(receiptDate)"
             + "BETWEEN ? AND ?" + "ORDER BY receiptDate DESC ";
        ResultSet rs = null;

        try (PreparedStatement stmt = conn.prepareStatement(sql);) {
            stmt.setString(1, dateOne);
            stmt.setString(2, dateTwo);
            rs = stmt.executeQuery();

            while (rs.next()) {
                SaleReports bean = new SaleReports();

                bean.setDate(rs.getString("receiptDate"));
                bean.setTicketNumber(rs.getString("ticketNumber"));
                bean.setSalesOutletId(rs.getString("salesOutletId"));
                bean.setReceiptId(rs.getInt("receiptsId"));
                bean.setNumberOfItems(rs.getInt("numberOfItems"));
                bean.setTotal(rs.getDouble("total"));
                bean.setModeOfPayment(rs.getString("modeOfPayment"));
                bean.setEmployeeLastName(rs.getString("lastName"));
                bean.setAmountPaid(rs.getDouble("amountPaid"));
                bean.setBalance(rs.getDouble("balance"));
                saleReportsList.add(bean);
            }

            return saleReportsList;
        }
    }

    public static ObservableList<SaleReports> getSaleportForThisMonth(int month) throws SQLException {
        ObservableList<SaleReports> saleReportsList = FXCollections.observableArrayList();
        String sql
             = "SELECT * FROM salereports WHERE MONTH(receiptDate) = ? "
             + "AND YEAR(receiptDate) = YEAR(CURDATE()) ORDER BY receiptDate DESC ";
        ResultSet rs = null;
        try (PreparedStatement stmt = conn.prepareStatement(sql);) {
            stmt.setInt(1, month);
            rs = stmt.executeQuery();

            while (rs.next()) {
                SaleReports bean = new SaleReports();

                bean.setDate(rs.getString("receiptDate"));
                bean.setTicketNumber(rs.getString("ticketNumber"));
                bean.setSalesOutletId(rs.getString("salesOutletId"));
                bean.setReceiptId(rs.getInt("receiptsId"));
                bean.setNumberOfItems(rs.getInt("numberOfItems"));
                bean.setTotal(rs.getDouble("total"));
                bean.setModeOfPayment(rs.getString("modeOfPayment"));
                bean.setEmployeeLastName(rs.getString("lastName"));
                bean.setAmountPaid(rs.getDouble("amountPaid"));
                bean.setBalance(rs.getDouble("balance"));
                saleReportsList.add(bean);
            }

            return saleReportsList;
        }
    }

    public static ObservableList<SaleReports> getSaleportForThisWeek() throws SQLException {
        ObservableList<SaleReports> saleReportsList = FXCollections.observableArrayList();
        String sql = "SELECT * FROM salereports "
             + "WHERE YEARWEEK(receiptDate, 1) = YEARWEEK(CURDATE(), 1) "
             + "ORDER BY receiptDate DESC";

        try (PreparedStatement stmt = conn.prepareStatement(sql);) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                SaleReports bean = new SaleReports();

                bean.setDate(rs.getString("receiptDate"));
                bean.setTicketNumber(rs.getString("ticketNumber"));
                bean.setSalesOutletId(rs.getString("salesOutletId"));
                bean.setReceiptId(rs.getInt("receiptsId"));
                bean.setNumberOfItems(rs.getInt("numberOfItems"));
                bean.setTotal(rs.getDouble("total"));
                bean.setModeOfPayment(rs.getString("modeOfPayment"));
                bean.setEmployeeLastName(rs.getString("lastName"));
                bean.setAmountPaid(rs.getDouble("amountPaid"));
                bean.setBalance(rs.getDouble("balance"));
                saleReportsList.add(bean);
            }

            return saleReportsList;
        }
    }

    public static ObservableList<SaleReports> getSaleportForToday() throws SQLException {
        ObservableList<SaleReports> saleReportsList = FXCollections.observableArrayList();

//      String sql = "SELECT * FROM salereport WHERE dateRange = ? ORDER BY DATE(date) asc";.
        String sql = "SELECT * FROM salereports WHERE DATE(receiptDate) = CURDATE() ORDER BY receiptDate DESC ";

        try (PreparedStatement stmt = conn.prepareStatement(sql);) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                SaleReports bean = new SaleReports();

                bean.setDate(rs.getString("receiptDate"));
                bean.setTicketNumber(rs.getString("ticketNumber"));
                bean.setSalesOutletId(rs.getString("salesOutletId"));
                bean.setReceiptId(rs.getInt("receiptsId"));
                bean.setNumberOfItems(rs.getInt("numberOfItems"));
                bean.setTotal(rs.getDouble("total"));
                bean.setModeOfPayment(rs.getString("modeOfPayment"));
                bean.setEmployeeLastName(rs.getString("lastName"));
                bean.setAmountPaid(rs.getDouble("amountPaid"));
                bean.setBalance(rs.getDouble("balance"));
                saleReportsList.add(bean);
            }

            return saleReportsList;
        }
    }

    public static ObservableList<SaleReports> getSaleportForYesterday() throws SQLException {
        ObservableList<SaleReports> saleReportsList = FXCollections.observableArrayList();
        String sql = "SELECT * FROM salereports "
             + "WHERE DATE(receiptDate) = CURDATE() - INTERVAL 1 DAY "
             + "ORDER BY receiptDate DESC ";

        try (PreparedStatement stmt = conn.prepareStatement(sql);) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                SaleReports bean = new SaleReports();

                bean.setDate(rs.getString("receiptDate"));
                bean.setTicketNumber(rs.getString("ticketNumber"));
                bean.setSalesOutletId(rs.getString("salesOutletId"));
                bean.setReceiptId(rs.getInt("receiptsId"));
                bean.setNumberOfItems(rs.getInt("numberOfItems"));
                bean.setTotal(rs.getDouble("total"));
                bean.setModeOfPayment(rs.getString("modeOfPayment"));
                bean.setEmployeeLastName(rs.getString("lastName"));
                bean.setAmountPaid(rs.getDouble("amountPaid"));
                bean.setBalance(rs.getDouble("balance"));
                saleReportsList.add(bean);
            }

            return saleReportsList;
        }
    }

    public static Integer getSumItemsSoldForAMonth(int month) throws SQLException {
        String sql = "select sum(numberOfItems) AS totalItemsSoldForToday "
             + "FROM salereports where MONTH(receiptDate) = ? AND YEAR(receiptDate) =  YEAR(NOW())";
        ResultSet rs = null;
        Integer totalSalestoday = null;

        try (PreparedStatement stmt = conn.prepareStatement(sql);) {
            stmt.setInt(1, month);
            rs = stmt.executeQuery();

            if (rs.next()) {
                totalSalestoday = rs.getInt(1);
            }
        }

        return totalSalestoday;
    }

    public static ObservableList<TopTenSelling> getTopTenSelling() throws SQLException {
        ObservableList<TopTenSelling> getTopTenSellingList = FXCollections.observableArrayList();
        String sql = "SELECT * FROM toptenselling";

        try (PreparedStatement stmt = conn.prepareStatement(sql);) {
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                TopTenSelling bean = new TopTenSelling();

                bean.setProductId(rs.getString("productId"));
                bean.setItemName(rs.getString("itemName"));
                bean.setQuantityBought(rs.getString("quantityBought"));
                getTopTenSellingList.add(bean);
            }

            return getTopTenSellingList;
        }
    }

    public static Integer getTotalItemsSoldForToday() throws SQLException {
        String sql = "SELECT * from totalitemssoldfortoday";
        ResultSet rs = null;
        int totalitemssoldfortoday = 0;

        try (PreparedStatement stmt = conn.prepareStatement(sql);) {
            rs = stmt.executeQuery();

            if (rs.next()) {
                totalitemssoldfortoday = rs.getInt(1);
            }
        }

        return totalitemssoldfortoday;
    }

    public static Double getTotalSalesForAMonth(int month) throws SQLException {
        String sql = "select FORMAT(SUM(total),2) as totalSalesForAMonth "
             + "FROM salereports where MONTH(receiptDate) = ? AND YEAR(receiptDate) = YEAR(NOW())";
        ResultSet rs = null;
        Double totalSalesForAMonth = 0.0;
//        System.out.println(sql);
        try (PreparedStatement stmt = conn.prepareStatement(sql);) {
            stmt.setInt(1, month);
            rs = stmt.executeQuery();

            if (rs.next()) {
                totalSalesForAMonth = rs.getDouble(1);
            }
        }

        return totalSalesForAMonth;
    }

    public static String getTotalSalesForAMonthIfJan(int month) throws SQLException {
        String sql = "select FORMAT(SUM(total),2) as totalSalesForAMonth "
             + "FROM salereports where MONTH(receiptDate) = ? AND YEAR(receiptDate) = YEAR(NOW()) - 1";
        ResultSet rs = null;
        String totalSalesForAMonth = null;
//        System.out.println(sql);
        try (PreparedStatement stmt = conn.prepareStatement(sql);) {
            stmt.setInt(1, month);
            rs = stmt.executeQuery();

            if (rs.next()) {
                totalSalesForAMonth = rs.getString(1);
            }
        }

        return totalSalesForAMonth;
    }

    public static Double getTotalSalesToday() throws SQLException {
        String sql = "SELECT * from  totalsalestoday";

        ResultSet rs = null;
        Double totalSalestoday = null;

        try (PreparedStatement stmt = conn.prepareStatement(sql);) {
            rs = stmt.executeQuery();

            if (rs.next()) {
                totalSalestoday = rs.getDouble(1);
            }
        }

        return totalSalestoday;
    }

    public static Integer getSumItemsSoldToday() throws SQLException {
        String sql = "SELECT * from  sumItemsSoldToday";

        ResultSet rs = null;
        int sumItemsSoldToday = 0;

        try (PreparedStatement stmt = conn.prepareStatement(sql);) {
            rs = stmt.executeQuery();

            if (rs.next()) {
                sumItemsSoldToday = rs.getInt(1);
            }
        }

        return sumItemsSoldToday;
    }
}


//~ Formatted by Jindent --- http://www.jindent.com
