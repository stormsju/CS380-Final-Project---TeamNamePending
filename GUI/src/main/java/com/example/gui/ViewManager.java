package com.example.gui;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;

import java.io.IOException;
import java.util.Objects;

public class ViewManager {

    private static Scene scene;

    public static void setScene(Scene scene) {
        ViewManager.scene = scene;
    }

    public static void switchTo(Views view) {
        if(scene == null) {
            System.out.println("No scene was set");
            return;
        }
        try {
            Parent root = FXMLLoader.load(Objects.requireNonNull(ViewManager.class.getResource(view.getFileName())));
            scene.setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
