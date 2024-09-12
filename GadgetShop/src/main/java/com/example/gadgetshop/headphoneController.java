package com.example.gadgetshop;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class headphoneController implements Initializable {
    BufferedReader reader;
    BufferedWriter writer;
    Socket sc = null;

    ObservableList<item> observableItemList = FXCollections.observableArrayList();
    @FXML
    private TableView<item> itemTableView;

    @FXML
    private TableColumn<item, String> columnName;

    @FXML
    private TableColumn<item, String> columnQuantity;

    @FXML
    private TableColumn<item, String> columnPrice;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        columnName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        columnQuantity.setCellValueFactory(new PropertyValueFactory<>("Quantity"));
        columnPrice.setCellValueFactory(new PropertyValueFactory<>("Price"));
        itemTableView.setItems(observableItemList);
        try {

            sc = new Socket("localhost", 1999);
            OutputStreamWriter o = new OutputStreamWriter(sc.getOutputStream());
            writer = new BufferedWriter(o);
            InputStreamReader isr = new InputStreamReader(sc.getInputStream());
            reader = new BufferedReader(isr);

            writer.write("HeadphoneData.txt" + "\n");
            writer.flush();
            int size = Integer.parseInt(reader.readLine());
            ArrayList<itemList> itemLists = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                itemList ac = new itemList(reader.readLine(),
                        reader.readLine(),reader.readLine());
                itemLists.add(ac);
            }
            for (itemList items : itemLists) {

                item item2 = new item(items.name,
                        items.quantity,items.price);
                observableItemList.add(item2);
            }

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
    void home(ActionEvent event) throws IOException {
        URL Page = getClass().getResource("adminHome.fxml");
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
