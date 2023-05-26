module admin.admingui {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
            
        requires org.controlsfx.controls;
                        requires org.kordamp.bootstrapfx.core;
            
    opens admin.admingui to javafx.fxml;
    exports admin.admingui;
}