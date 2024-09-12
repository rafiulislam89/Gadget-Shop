package com.example.gadgetshop;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.Button;

public class userItemList {
    public final SimpleStringProperty name;
    public final SimpleStringProperty quantity;
    public final SimpleStringProperty price;
    public  Button button;


    public userItemList(String name, String quantity, String price , Button button) {
        this.name = new SimpleStringProperty(name);
        this.quantity = new SimpleStringProperty(quantity);
        this.price = new SimpleStringProperty(price);
        this.button = button;
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

    public void setButton(Button button) { this.button = button; }

    public Button getButton(){ return button; }

}
