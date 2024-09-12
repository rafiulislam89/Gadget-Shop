package com.example.gadgetshop;

import javafx.scene.control.Button;

public class userItem {
    String name;
    String quantity;
    String price;
    Button button;
    public userItem (String Name, String quantity , String price, Button button) {
        this.name = Name;
        this.quantity = quantity;
        this.price = price;
        this.button = button;
    }
}
