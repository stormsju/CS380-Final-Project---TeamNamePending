package com.example.gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import state.HomeState;
import state.PictureSelectState;
import state.ProfileState;

public class PicMeGUI extends Application {
    static HomeState hs = new HomeState();
    static PictureSelectState pss = new PictureSelectState();
    static ProfileState ps = new ProfileState();

    @Override
    public void start(Stage stage) {

        var scene = new Scene(new Pane());
        ViewManager.setScene(scene);
        ViewManager.switchTo(Views.HOME);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        HomeState hs = new HomeState();
        PictureSelectState pss = new PictureSelectState();
        ProfileState ps = new ProfileState();

        launch();
    }
}