package com.example.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

public class PictureSelectController {

    @FXML
    private Button btnHome;

    @FXML
    private Button btnPhotos;

    @FXML
    private Button btnProfile;

    @FXML
    private ImageView imgSelect1;

    @FXML
    private ImageView imgSelect2;

    @FXML
    private ImageView imgSelect3;

    @FXML
    private ImageView imgSelect4;

    @FXML
    private ImageView imgSelect5;

    @FXML
    private ImageView imgSelect6;

    @FXML
    private ImageView imgSelect7;

    @FXML
    private ImageView imgSelect8;

    @FXML
    private ImageView imgSelect9;

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