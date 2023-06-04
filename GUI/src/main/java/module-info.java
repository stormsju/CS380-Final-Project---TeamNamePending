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
    requires java.desktop;

    opens com.example.gui to javafx.fxml;
    opens entity to com.google.gson;
    exports com.example.gui;
    exports entity;
    exports http;
}