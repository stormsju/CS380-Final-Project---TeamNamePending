module admin.admingui {
    requires javafx.controls;
    requires javafx.fxml;
            
        requires org.controlsfx.controls;
                        requires org.kordamp.bootstrapfx.core;
            
    opens admin.admingui to javafx.fxml;
    exports admin.admingui;
}