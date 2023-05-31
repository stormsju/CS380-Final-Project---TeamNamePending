package com.example.gui;

import javafx.fxml.FXML;

public class PictureSelectController {

    @FXML
    protected void homeNavButtonPressed() {
        ViewManager.switchTo(Views.HOME);
    }

    @FXML
    protected void photoNavButtonPressed() {
        ViewManager.switchTo(Views.PICTURESELECT);
    }

    @FXML
    protected void profileNavButtonPressed() {
        ViewManager.switchTo(Views.PROFILE);
    }
}
