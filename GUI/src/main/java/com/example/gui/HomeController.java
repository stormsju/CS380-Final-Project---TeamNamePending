package com.example.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

public class HomeController {

    @FXML
    private Button btnHome;

    @FXML
    private Button btnPhotos;

    @FXML
    private Button btnProfile;

    @FXML
    private ImageView imgFeed;

    @FXML
    private Label tfCaption;

    @FXML
    private Hyperlink tfUsername;

    @FXML
    void homeNavButtonPressed(ActionEvent event) {
        ViewManager.switchTo(Views.HOME);
    }

    @FXML
    void photoNavButtonPressed(ActionEvent event) {
        ViewManager.switchTo(Views.PICTURESELECT);
    }

    @FXML
    void profileNavButtonPressed(ActionEvent event) {
        ViewManager.switchTo(Views.PROFILE);
    }
}