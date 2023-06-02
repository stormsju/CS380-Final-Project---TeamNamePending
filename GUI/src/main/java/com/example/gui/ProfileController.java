package com.example.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ProfileController {

    @FXML
    private Button btnHome;

    @FXML
    private Button btnPhotos;

    @FXML
    private Button btnProfile;

    @FXML
    private ImageView imgFeed;

    @FXML
    private Label tfProfile;

    public void setPhoto(Image image) {

        // INSERT CODE HERE:
        // Set the image in imgFeed with the given Image.

    }

    public void setProfileInfo() {

        // INSERT CODE HERE:
        // Set the caption to the photo to display the user's information like first name, last name, and email.
        // Will need params.

    }

    @FXML
    void homeNavButtonPressed(ActionEvent event) {

        HomeController hc = new HomeController();
        hc.homeNavButtonPressed();
    }

    @FXML
    void photoNavButtonPressed(ActionEvent event) {

        PictureSelectController psc = new PictureSelectController();
        psc.photoNavButtonPressed();
    }

    @FXML
    public void profileNavButtonPressed() {

        // INSERT CODE HERE:
        // Get the selected post chosen by the user from the picture selection screen.
        // Put the file for the photo from the post into imgFeed.setImage(imageFile:string)
        // Put the caption from the post into tfCaption.setText(caption:string)

        ViewManager.switchTo(Views.PROFILE);
    }
}