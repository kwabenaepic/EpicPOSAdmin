/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

/**
 *
 * @author KwabenaEpic
 */
import javafx.beans.property.*;

public class SaleReports {

    private final StringProperty date;
    private final StringProperty ticketNumber;
    private final StringProperty salesOutletId;
    private final IntegerProperty receiptId;
    private final IntegerProperty numberOfItems;
    private final DoubleProperty total;
    private final StringProperty modeOfPayment;
    private final StringProperty employeeLastName;
    private final DoubleProperty amountPaid;
    private final DoubleProperty balance;

    public SaleReports() {
        this.date = new SimpleStringProperty();
        this.ticketNumber = new SimpleStringProperty();
        this.salesOutletId = new SimpleStringProperty();
        this.receiptId = new SimpleIntegerProperty();
        this.numberOfItems = new SimpleIntegerProperty();
        this.total = new SimpleDoubleProperty();
        this.modeOfPayment = new SimpleStringProperty();
        this.employeeLastName = new SimpleStringProperty();
        this.amountPaid = new SimpleDoubleProperty();
        this.balance = new SimpleDoubleProperty();

    }

    public final void setDate(String value) {
        date.set(value);
    }

    public final String getDate() {
        return date.get();
    }

    public final StringProperty dateProperty() {
        return date;
    }

    public final void setTicketNumber(String value) {
        ticketNumber.set(value);
    }

    public final String getTicketNumber() {
        return ticketNumber.get();
    }

    public final StringProperty ticketNumberProperty() {
        return ticketNumber;
    }

    public final void setSalesOutletId(String value) {
        salesOutletId.set(value);
    }

    public final String getSalesOutletId() {
        return salesOutletId.get();
    }

    public final StringProperty salesOutletIdProperty() {
        return salesOutletId;
    }

    public final void setReceiptId(Integer value) {
        receiptId.set(value);
    }

    public final Integer getReceiptId() {
        return receiptId.get();
    }

    public final IntegerProperty receiptIdProperty() {
        return receiptId;
    }

    public final void setNumberOfItems(Integer value) {
        numberOfItems.set(value);
    }

    public final Integer getNumberOfItems() {
        return numberOfItems.get();
    }

    public final IntegerProperty numberOfItemsProperty() {
        return numberOfItems;
    }

    public final void setTotal(Double value) {
        total.set(value);
    }

    public final Double getTotal() {
        return total.get();
    }

    public final DoubleProperty totalProperty() {
        return total;
    }

    public final void setModeOfPayment(String value) {
        modeOfPayment.set(value);
    }

    public final String getModeOfPayment() {
        return modeOfPayment.get();
    }

    public final StringProperty modeOfPaymentProperty() {
        return modeOfPayment;
    }

    public final void setEmployeeLastName(String value) {
        employeeLastName.set(value);
    }

    public final String getEmployeeLastName() {
        return employeeLastName.get();
    }

    public final StringProperty employeeLastNameProperty() {
        return employeeLastName;
    }

    public final void setAmountPaid(Double value) {
        amountPaid.set(value);
    }

    public final Double getAmountPaid() {
        return amountPaid.get();
    }

    public final DoubleProperty amountPaidProperty() {
        return amountPaid;
    }

    public final void setBalance(Double value) {
        balance.set(value);
    }

    public final Double getBalance() {
        return balance.get();
    }

    public final DoubleProperty balanceProperty() {
        return balance;
    }

}
