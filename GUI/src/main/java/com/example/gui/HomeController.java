package com.example.gui;

import javafx.fxml.FXML;

public class HomeController {

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

    @FXML
    protected void searchButtonPressed() {
        ViewManager.switchTo(Views.SEARCH);
    }

    @FXML
    protected void settingsButtonPressed() {
        ViewManager.switchTo(Views.SETTINGS);
    }

    @FXML
    protected void usernameLinkClicked() {

    }

    @FXML
    protected void likesLinkClicked() {

    }

    @FXML
    protected void commentsLinkClicked() {

    }
}
