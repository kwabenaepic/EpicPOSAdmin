/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package beans;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author KwabenaEpic
 */
public class TopTenSelling {

    public final void setProductId(String value) {
        productId.set(value);
    }

    public final String getProductId() {
        return productId.get();
    }

    public final StringProperty productIdProperty() {
        return productId;
    }

    public final void setItemName(String value) {
        itemName.set(value);
    }

    public final String getItemName() {
        return itemName.get();
    }

    public final StringProperty itemNameProperty() {
        return itemName;
    }

    public final void setQuantityBought(String value) {
        quantityBought.set(value);
    }

    public final String getQuantityBought() {
        return quantityBought.get();
    }

    public final StringProperty quantityBoughtProperty() {
        return quantityBought;
    }

    private final StringProperty productId;
    private final StringProperty itemName;
    private final StringProperty quantityBought;

    public TopTenSelling() {
        this.productId = new SimpleStringProperty();
        this.itemName = new SimpleStringProperty();
        this.quantityBought = new SimpleStringProperty();
    }
}
