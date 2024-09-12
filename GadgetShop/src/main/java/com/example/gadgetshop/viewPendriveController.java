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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.ResourceBundle;

public class viewPendriveController implements Initializable {

    BufferedReader reader;
    BufferedWriter writer;
    Socket sc = null;
    Socket bc = null;
    ArrayList<userItem> itemLists = new ArrayList<>();
    ObservableList<userItemList> observableItemList = FXCollections.observableArrayList();
    int size;

    Button []button = new Button[10];
    @FXML
    private TableView<userItemList> itemTableView;

    @FXML
    private TableColumn<userItemList, String> columnName;

    @FXML
    private TableColumn<userItemList, String> columnQuantity;

    @FXML
    private TableColumn<userItemList, String> columnPrice;

    @FXML
    private TableColumn<userItemList, String> columnDetails;

    private void handleButtonAction(ActionEvent event) {


        try {
            // FileWriter Writer = new FileWriter("viewFile.txt", true);
            bc = new Socket("localhost", 2);
            OutputStreamWriter o = new OutputStreamWriter(bc.getOutputStream());
            writer = new BufferedWriter(o);
            InputStreamReader isr = new InputStreamReader(bc.getInputStream());
            reader = new BufferedReader(isr);

            for(int i=0 ; i< button.length; i++){
                if(Objects.equals(event.getSource(),button[i])){

                    writer.write("Pendrive" + "\n");
                    writer.write(itemLists.get(i).name + "\n");
                    writer.write(itemLists.get(i).price + "\n");
                    writer.flush();
                    URL Page = getClass().getResource("buy.fxml");
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
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        columnName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        columnQuantity.setCellValueFactory(new PropertyValueFactory<>("Quantity"));
        columnPrice.setCellValueFactory(new PropertyValueFactory<>("Price"));
        columnDetails.setCellValueFactory(new PropertyValueFactory<>("button"));
        itemTableView.setItems(observableItemList);

        for(int i = 0; i<button.length ; i++) {
            button[i] = new Button();
            button[i].setText("View");
            button[i].setOnAction(this::handleButtonAction);
        }

        try {

            sc = new Socket("localhost", 1999);
            OutputStreamWriter o = new OutputStreamWriter(sc.getOutputStream());
            writer = new BufferedWriter(o);
            InputStreamReader isr = new InputStreamReader(sc.getInputStream());
            reader = new BufferedReader(isr);

            writer.write("PendriveData.txt" + "\n");
            writer.flush();
            size = Integer.parseInt(reader.readLine());

            for (int i = 0; i < size; i++) {
                userItem ac = new userItem(reader.readLine(),
                        reader.readLine(),reader.readLine(),button[i]);
                itemLists.add(ac);
            }

            for (userItem items : itemLists) {

                userItemList item2 = new userItemList(items.name,
                        items.quantity,items.price,items.button);
                observableItemList.add(item2);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
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
