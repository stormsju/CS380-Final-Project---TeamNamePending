module com.example.gui {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.controlsfx.controls;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires lombok;
    requires com.google.gson;
    requires java.sql;
    requires java.net.http;

    opens com.example.gui to javafx.fxml;
    exports com.example.gui;
}