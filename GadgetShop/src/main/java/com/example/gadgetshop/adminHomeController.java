package com.example.gadgetshop;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class adminHomeController implements Initializable {
    BufferedReader reader;
    BufferedWriter writer;
    Socket sc = null;

    @FXML
    private TextField itemName;

    @FXML
    private TextField itemQuantity;

    @FXML
    private TextField itemPrice;

    @FXML
    private ChoiceBox<String> itemType;

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        itemType.setValue("Type");
        itemType.getItems().addAll("Headphone","Charger","Pendrive");
    }

    @FXML
    void addItem(ActionEvent event) {
        try {

            sc = new Socket("localhost", 9997);
            OutputStreamWriter o = new OutputStreamWriter(sc.getOutputStream());
            writer = new BufferedWriter(o);

            InputStreamReader isr = new InputStreamReader(sc.getInputStream());
            reader = new BufferedReader(isr);
            writer.write( itemType.getValue() + "\n");
            writer.write(itemName.getText() + "\n");
            writer.write(itemQuantity.getText() + "\n");
            writer.write(itemPrice.getText() + "\n");
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void charger(ActionEvent event) throws IOException {
        URL Page = getClass().getResource("charger.fxml");
        if (Page == null) {
            return;
        }
        Parent parent = FXMLLoader.load(Page);
        Scene view = new Scene(parent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(view);
        window.show();
    }

    @FXML
    void headphone(ActionEvent event) throws IOException {
        URL Page = getClass().getResource("headphone.fxml");
        if (Page == null) {
            return;
        }
        Parent parent = FXMLLoader.load(Page);
        Scene view = new Scene(parent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(view);
        window.show();
    }

    @FXML
    void logOut(ActionEvent event) throws IOException {
        URL Page = getClass().getResource("LogIn.fxml");
        if (Page == null) {
            return;
        }
        Parent parent = FXMLLoader.load(Page);
        Scene view = new Scene(parent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(view);
        window.show();
    }

    @FXML
    void pendrive(ActionEvent event) throws IOException {
        URL Page = getClass().getResource("pendrive.fxml");
        if (Page == null) {
            return;
        }
        Parent parent = FXMLLoader.load(Page);
        Scene view = new Scene(parent);
        Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
        window.setScene(view);
        window.show();
    }

}
