module com.example.feereport {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires java.sql;


    opens com.example.feereport to javafx.fxml;
    exports com.example.feereport;
}