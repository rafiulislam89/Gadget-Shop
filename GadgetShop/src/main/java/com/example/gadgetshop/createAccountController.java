package com.example.gadgetshop;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.URL;

public class createAccountController {
    BufferedReader reader;
    BufferedWriter writer;
    Socket sc = null;

    @FXML
    private TextField userName;

    @FXML
    private TextField password;

    @FXML
    private TextField email;

    @FXML
    private TextField phoneNumber;

    @FXML
    void create(ActionEvent event) throws Exception {
        Account account = new Account(userName.getText(), password.getText(), email.getText(),Integer.parseInt(phoneNumber.getText()));

            sc = new Socket("localhost", 2345);
            OutputStreamWriter o = new OutputStreamWriter(sc.getOutputStream());
            writer = new BufferedWriter(o);
            InputStreamReader isr = new InputStreamReader(sc.getInputStream());
            reader = new BufferedReader(isr);

            writer.write(account.name + "\n");
            writer.write(account.password + "\n");
            writer.write(account.email + "\n");
            writer.write(account.phone + "\n");
            writer.flush();


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
    void Return(ActionEvent event) throws Exception {

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
}
