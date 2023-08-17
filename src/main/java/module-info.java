module com.example.demo {
 requires javafx.controls;
 requires javafx.fxml;
    requires java.sql;
    requires org.jsoup;
    requires desktop;
    requires java.desktop;


    opens com.example.demo to javafx.fxml;
    exports com.example.demo;
    exports com.example.demo.Database;
    opens com.example.demo.Database to javafx.fxml;
}