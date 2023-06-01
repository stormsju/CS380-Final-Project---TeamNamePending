package com.admin.admingui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class CreateNewAdmin extends Application{
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AdminLogin.class.getResource("CreateNewAdmin.fxml"));
        Pane dialogPane = fxmlLoader.load();



        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("New Admin User");
        stage.setScene(scene);
        stage.show();
    }
    public static void launchUI() {
        launch();
    }
}
