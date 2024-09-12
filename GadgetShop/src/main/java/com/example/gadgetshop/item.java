package com.example.gadgetshop;

import javafx.beans.property.SimpleStringProperty;

public class item {
    public final SimpleStringProperty name;
    public final SimpleStringProperty quantity;
    public final SimpleStringProperty price;


    public item(String name, String quantity, String price) {
        this.name = new SimpleStringProperty(name);
        this.quantity = new SimpleStringProperty(quantity);
        this.price = new SimpleStringProperty(price);
    }

    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public String getQuantity() { return quantity.get(); }

    public void setQuantity(String quantity) { this.quantity.set(quantity); }

    public SimpleStringProperty quantityProperty() {
        return quantity;
    }

    public String getPrice() { return price.get(); }

    public void setPrice(String price) { this.price.set(price); }

    public SimpleStringProperty priceProperty() {
        return price;
    }

}
