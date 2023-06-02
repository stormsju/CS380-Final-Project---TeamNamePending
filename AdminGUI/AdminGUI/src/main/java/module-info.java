module admin.admingui {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
            
        requires org.controlsfx.controls;
                        requires org.kordamp.bootstrapfx.core;
    requires com.google.gson;
    requires java.net.http;
    requires lombok;

    opens admin.admingui to javafx.fxml;
    exports admin.admingui;
    exports admin.entity;
    opens admin.entity to javafx.fxml;
    exports admin.http;
    opens admin.http to javafx.fxml;
    opens com.admin.admingui to javafx.fxml;
}