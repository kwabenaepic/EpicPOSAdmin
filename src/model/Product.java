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
public class Product {

    private final DoubleProperty price;

    private final StringProperty description;

    private final IntegerProperty quantity;
    private final StringProperty productId;

    public Product(String productId, String description, Double price, Integer quantity) {
        this.productId = new SimpleStringProperty(productId);
        this.description = new SimpleStringProperty(description);
        this.price = new SimpleDoubleProperty(price);
        this.quantity = new SimpleIntegerProperty(quantity);
    }

    //Getters
    public Double getPrice() {
        return price.get();
    }

    public String getProductID() {
        return productId.get();
    }

    public String getDescription() {
        return description.get();
    }

    public int getQuantity() {
        return quantity.get();
    }

    //Setters
    public void setPrice(Double value) {
        price.set(value);
    }

    public void setProductID(String value) {
        productId.set(value);
    }

    public void setDescription(String value) {
        description.set(value);
    }

    public void setQuantity(int value) {
        quantity.set(value);
    }

    // property values
    public StringProperty descriptionProperty() {
        return description;
    }

    public StringProperty productIdProperty() {
        return productId;
    }

    public IntegerProperty quantityProperty() {
        return quantity;
    }
}
