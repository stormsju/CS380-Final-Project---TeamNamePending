package com.example.gui;

import data.DataSingleton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    DataSingleton data = DataSingleton.getInstance();

    @FXML
    private ImageView imgFeed;
    @FXML
    private Label tfCaption;
    @FXML
    private Hyperlink tfUsername;
    @FXML
    private Label tfDate;

    @FXML
    void photoNavButtonPressed() {
        ViewManager.switchTo(Views.PICTURESELECT);
    }

    @FXML
    void profileNavButtonPressed() {
        ViewManager.switchTo(Views.PROFILE);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        imgFeed.setImage(data.getImg());
        tfCaption.setText(data.getCaption());
        tfUsername.setText(data.getUsername());
        tfDate.setText(data.getDate());
    }
}