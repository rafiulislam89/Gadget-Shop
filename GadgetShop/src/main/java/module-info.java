module com.example.gadgetshop {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.gadgetshop to javafx.fxml;
    exports com.example.gadgetshop;
}