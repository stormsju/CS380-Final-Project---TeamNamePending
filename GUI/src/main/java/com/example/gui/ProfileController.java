package com.example.gui;

import data.DataSingleton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import java.net.URL;
import java.util.ResourceBundle;

public class ProfileController implements Initializable {

    DataSingleton data = DataSingleton.getInstance();

    @FXML
    private ImageView imgFeed;
    @FXML
    private Label tfProfile;
    @FXML
    private Hyperlink tfUsername;
    @FXML
    private Label tfDate;

    @FXML
    void homeNavButtonPressed() {
        ViewManager.switchTo(Views.HOME);
    }

    @FXML
    void photoNavButtonPressed() {
        ViewManager.switchTo(Views.PICTURESELECT);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        imgFeed.setImage(data.getImg());
        tfProfile.setText(data.getUserInfo());
        tfUsername.setText(data.getUsername());
        tfDate.setText(data.getDate());
    }
}