package com.example.gadgetshop;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

public class buyController implements Initializable {

    BufferedReader reader;
    BufferedWriter writer;
    Socket sc = null;
    Socket bc = null;
    String type;
    @FXML
    private ImageView pImage;

    @FXML
    private Label price;

    @FXML
    private Label name;

    @FXML
    private Label buyPrice;

    @FXML
    private Spinner<Integer> quantitySpinner;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 10);
        valueFactory.setValue(0);
        quantitySpinner.setValueFactory(valueFactory);

        try {

            sc = new Socket("localhost", 69);
            OutputStreamWriter o = new OutputStreamWriter(sc.getOutputStream());
            writer = new BufferedWriter(o);
            InputStreamReader isr = new InputStreamReader(sc.getInputStream());
            reader = new BufferedReader(isr);

            type = reader.readLine();
            String Name = reader.readLine();
            price.setText(reader.readLine());
            name.setText(Name);
            Image myImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream(Name+".PNG")));
            pImage.setImage(myImage);

        }catch (Exception e){
            e.printStackTrace();
        }

                    int p = Integer.parseInt(price.getText())*quantitySpinner.getValue();
                    buyPrice.setText( String.valueOf(p));

        quantitySpinner.valueProperty().addListener(new ChangeListener<Integer>() {

            @Override
            public void changed(ObservableValue<? extends Integer> arg0, Integer arg1, Integer arg2) {

                int p = Integer.parseInt(price.getText())*quantitySpinner.getValue();
                buyPrice.setText( String.valueOf(p));

            }
        });
    }


    @FXML
    void buy(ActionEvent event) throws IOException {

        bc = new Socket("localhost", 9997);
        OutputStreamWriter o = new OutputStreamWriter(bc.getOutputStream());
        writer = new BufferedWriter(o);

        int a = -1*(quantitySpinner.getValue());
        writer.write(type + "\n");
        writer.write(name.getText() + "\n");
        writer.write(a + "\n");
        writer.write("00" + "\n");
        writer.flush();

        URL Page = getClass().getResource("purchase.fxml");
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

}
