package com.example.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

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

        HomeController hc = new HomeController();
        hc.homeNavButtonPressed();
    }

    @FXML
    public void photoNavButtonPressed() {

        // INSERT CODE HERE:
        // Fill the 3 x 3 grid with images from posts from the database.
        // There are 9 available spots.
        // To change the first spot, imgSelect1.setImage(imageFile:string)
        // To change second spot, imgSelect2.setImage(imageFile:string)
        // and so on until ninth spot, imgSelect9.setImage(imageFile:string)

        ViewManager.switchTo(Views.PICTURESELECT);
    }

    @FXML
    void profileNavButtonPressed(ActionEvent event) {

        ProfileController pc = new ProfileController();
        pc.profileNavButtonPressed();
    }

    @FXML
    void pic1Clicked(MouseEvent event) {

        // INSERT CODE HERE:
        // Set the clicked post as the post shown in the Home page.

        HomeController hc = new HomeController();

        if(imgSelect1.getImage() != null) {
            ViewManager.switchTo(Views.HOME);
        }
    }

    @FXML
    void pic2Clicked(MouseEvent event) {

        // INSERT CODE HERE:
        // Set the clicked post as the post shown in the Home page.

        if(imgSelect2.getImage() != null) {
            ViewManager.switchTo(Views.HOME);
        }
    }

    @FXML
    void pic3Clicked(MouseEvent event) {

        // INSERT CODE HERE:
        // Set the clicked post as the post shown in the Home page.

        if(imgSelect3.getImage() != null) {
            ViewManager.switchTo(Views.HOME);
        }
    }

    @FXML
    void pic4Clicked(MouseEvent event) {

        // INSERT CODE HERE:
        // Set the clicked post as the post shown in the Home page.

        if(imgSelect4.getImage() != null) {
            ViewManager.switchTo(Views.HOME);
        }
    }

    @FXML
    void pic5Clicked(MouseEvent event) {

        // INSERT CODE HERE:
        // Set the clicked post as the post shown in the Home page.

        if(imgSelect5.getImage() != null) {
            ViewManager.switchTo(Views.HOME);
        }
    }

    @FXML
    void pic6Clicked(MouseEvent event) {

        // INSERT CODE HERE:
        // Set the clicked post as the post shown in the Home page.

        if(imgSelect6.getImage() != null) {
            ViewManager.switchTo(Views.HOME);
        }
    }

    @FXML
    void pic7Clicked(MouseEvent event) {

        // INSERT CODE HERE:
        // Set the clicked post as the post shown in the Home page.

        if(imgSelect7.getImage() != null) {
            ViewManager.switchTo(Views.HOME);
        }
    }

    @FXML
    void pic8Clicked(MouseEvent event) {

        // INSERT CODE HERE:
        // Set the clicked post as the post shown in the Home page.

        if(imgSelect8.getImage() != null) {
            ViewManager.switchTo(Views.HOME);
        }
    }

    @FXML
    void pic9Clicked(MouseEvent event) {

        // INSERT CODE HERE:
        // Set the clicked post as the post shown in the Home page.

        if(imgSelect9.getImage() != null) {
            ViewManager.switchTo(Views.HOME);
        }
    }
}