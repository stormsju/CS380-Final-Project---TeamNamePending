package com.example.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class ProfileController {

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
