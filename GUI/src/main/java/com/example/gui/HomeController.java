package com.example.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.io.FileInputStream;

public class HomeController {

    @FXML
    private Button btnHome;

    @FXML
    private Button btnPhotos;

    @FXML
    private Button btnProfile;

    @FXML
    private ImageView imgFeed = new ImageView();

    @FXML
    private Label tfCaption;

    public void setPhoto(String directory) {

        // INSERT CODE HERE:
        // Set the image in imgFeed with the given Image.

        try{
            FileInputStream stream = new FileInputStream(directory);
            Image image = new Image(stream);
            imgFeed.setImage(image);
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public void setCaption(String caption) {

        // INSERT CODE HERE:
        // Set the caption with the given String.

    }

    @FXML
    public void homeNavButtonPressed() {

        // INSERT CODE HERE:
        // Get the selected post chosen by the user from the picture selection screen.
        // Put the file for the photo from the post into imgFeed.setImage(image:Image)
        // Put the caption from the post into tfCaption.setText(caption:string)

        setPhoto("alternatePhoto.jpg");
        ViewManager.switchTo(Views.HOME);
    }

    @FXML
    void photoNavButtonPressed(ActionEvent event) {

        PictureSelectController psc = new PictureSelectController();
        psc.photoNavButtonPressed();
    }

    @FXML
    void profileNavButtonPressed(ActionEvent event) {

        ProfileController pc = new ProfileController();
        pc.profileNavButtonPressed();
    }
}