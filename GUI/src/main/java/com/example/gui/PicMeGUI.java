package com.example.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class PicMeGUI extends Application {

    @Override
    public void start(Stage stage) {

        var scene = new Scene(new Pane());
        ViewManager.setScene(scene);
        ViewManager.switchTo(Views.PICTURESELECT);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {

        launch();
    }
}