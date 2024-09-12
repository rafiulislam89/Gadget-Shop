package com.example.gadgetshop;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.*;
import java.net.Socket;
import java.net.URL;

public class editProfileController {
    BufferedReader reader;
    BufferedWriter writer;
    Socket sc = null;
    @FXML
    private TextField name;

    @FXML
    private TextField password;

    @FXML
    private TextField newName;

    @FXML
    private TextField newPassword;

    @FXML
    void Return(ActionEvent event) throws IOException {
        URL Page = getClass().getResource("user.fxml");
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
    void change(ActionEvent event) {
        try {
            sc = new Socket("localhost", 6543);
            OutputStreamWriter o = new OutputStreamWriter(sc.getOutputStream());
            writer = new BufferedWriter(o);
            writer.write(name.getText() + "\n");
            writer.write(password.getText() + "\n");
            writer.write(newName.getText() + "\n");
            writer.write(newPassword.getText() + "\n");
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
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
