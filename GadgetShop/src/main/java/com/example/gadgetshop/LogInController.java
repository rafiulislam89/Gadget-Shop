package com.example.gadgetshop;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.util.Objects;

public class LogInController {
    BufferedReader reader;
    BufferedWriter writer;
    Socket sc = null;

    @FXML
    private TextField userName;

    @FXML
    private PasswordField userPassword;


    @FXML
    void createAccount(ActionEvent event) throws Exception {

        URL Page = getClass().getResource("createAccount.fxml");
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
    void userlogin(ActionEvent event) {

        String name = userName.getText();
        String password = userPassword.getText();

        try {

            sc = new Socket("localhost", 1234);
            OutputStreamWriter o = new OutputStreamWriter(sc.getOutputStream());
            writer = new BufferedWriter(o);

            InputStreamReader isr = new InputStreamReader(sc.getInputStream());
            reader = new BufferedReader(isr);
            writer.write(name + "\n");
            writer.write(password + "\n");
            writer.flush();
            int i =Integer.parseInt(reader.readLine());
            if (Objects.equals(i,1)) {
                URL AdminPage = getClass().getResource("adminHome.fxml");

                if (AdminPage == null) {
                    return;
                }
                Parent parent = FXMLLoader.load(AdminPage);
                Scene view = new Scene(parent);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(view);
                window.show();
            }
            else if (Objects.equals(i,2)) {
                URL AdminPage = getClass().getResource("user.fxml");

                if (AdminPage == null) {
                    return;
                }
                Parent parent = FXMLLoader.load(AdminPage);
                Scene view = new Scene(parent);
                Stage window = (Stage) ((Node) event.getSource()).getScene().getWindow();
                window.setScene(view);
                window.show();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}