module com.example.demo {
    requires javafx.controls;
    requires javafx.fxml;
    requires jbox2d.library;


    opens com.example.demo to javafx.fxml;
    exports com.example.demo;
}