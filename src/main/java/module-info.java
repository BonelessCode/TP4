module com.example.tp4_v2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.tp4_v2 to javafx.fxml;
    exports com.example.tp4_v2;
}