/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import javafx.beans.property.*;

/**
 *
 * @author KwabenaEpic
 */
public class SalesTransaction {

    private final StringProperty transactionId;
    private final StringProperty customerId;
    private final StringProperty salesOutletId;
    private final StringProperty staffId;
    private final StringProperty transactionDate;
    private final DoubleProperty transactionAmount;

    public SalesTransaction(String transactionId, String customerId, String salesOutletId, String staffId, String transactionDate, Double transactionAmount) {
        this.transactionId = new SimpleStringProperty(transactionId);
        this.customerId = new SimpleStringProperty(customerId);
        this.salesOutletId = new SimpleStringProperty(salesOutletId);
        this.staffId = new SimpleStringProperty(staffId);
        this.transactionDate = new SimpleStringProperty(transactionId);
        this.transactionAmount = new SimpleDoubleProperty(transactionAmount);

    }

    public String getTransactionId() {
        return transactionId.get();
    }

    public void setTransactionId(String value) {
        transactionId.set(value);
    }

    public String getCustomerId() {
        return customerId.get();
    }

    public void setCustomerId(String value) {
        customerId.set(value);
    }

    public String getSalesOutletId() {
        return salesOutletId.get();
    }

    public void setSalesOutletId(String value) {
        salesOutletId.set(value);
    }

    /**
     * @return the staffId
     */
    public String getStaffId() {
        return staffId.get();
    }

    public void setStaffId(String value) {
        staffId.set(value);
    }

    /**
     * @return the transactionDate
     */
    public String getTransactionDate() {
        return transactionDate.get();
    }

    public void setTransactionDate(String value) {
        transactionDate.set(value);
    }

    /**
     * @return the TransactionAmount
     */
    public Double getTransactionAmount() {
        return transactionAmount.get();
    }

    public void setTransactionAmount(Double value) {
        transactionAmount.set(value);
    }

}
