package com.example.gui;

import data.DataSingleton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import java.net.URL;
import java.util.ResourceBundle;

public class PictureSelectController implements Initializable {

    DataSingleton data = DataSingleton.getInstance();

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
    void homeNavButtonPressed() {
        ViewManager.switchTo(Views.HOME);
    }

    @FXML
    void profileNavButtonPressed() {
        ViewManager.switchTo(Views.PROFILE);
    }

    @FXML
    void pic1Clicked(MouseEvent event) { // Clicking on this pic sets the data stored in DataSingleton to store the info
                                         // of all the information that corresponds with that photo, like the actual
                                         // photo as an Image object, the username of the poster, the date of the post,
                                         // the caption, and the poster's information.

        // This if statement doesn't allow any functionality when clicked if the ImageView is empty.
        if(imgSelect1.getImage() != null) {

            // INSERT CODE HERE:
            // Repeat for all click events for the pictures:
            // Place the corresponding information of this photo clicked, as data in the singleton.
            // Example:
            // data.setImg(insert Image object)
            // data.setCaption(insert String)
            // data.setUserInfo(insert String)
            // data.setDate(insert String)
            // data.setUsername(insert String)

            data.setImg(imgSelect1.getImage()); // Delete this once the above code is implemented. This was temporary
                                                // to see the code working but will need to be removed.

            ViewManager.switchTo(Views.HOME);
        }
    }

    @FXML
    void pic2Clicked(MouseEvent event) {

        if(imgSelect2.getImage() != null) {
            data.setImg(imgSelect2.getImage());
            ViewManager.switchTo(Views.HOME);
        }
    }

    @FXML
    void pic3Clicked(MouseEvent event) {

        if(imgSelect3.getImage() != null) {
            data.setImg(imgSelect3.getImage());
            ViewManager.switchTo(Views.HOME);
        }
    }

    @FXML
    void pic4Clicked(MouseEvent event) {

        if(imgSelect4.getImage() != null) {
            data.setImg(imgSelect4.getImage());
            ViewManager.switchTo(Views.HOME);
        }
    }

    @FXML
    void pic5Clicked(MouseEvent event) {

        if(imgSelect5.getImage() != null) {
            data.setImg(imgSelect5.getImage());
            ViewManager.switchTo(Views.HOME);
        }
    }

    @FXML
    void pic6Clicked(MouseEvent event) {

        if(imgSelect6.getImage() != null) {
            data.setImg(imgSelect6.getImage());
            ViewManager.switchTo(Views.HOME);
        }
    }

    @FXML
    void pic7Clicked(MouseEvent event) {

        if(imgSelect7.getImage() != null) {
            data.setImg(imgSelect7.getImage());
            ViewManager.switchTo(Views.HOME);
        }
    }

    @FXML
    void pic8Clicked(MouseEvent event) {

        if(imgSelect8.getImage() != null) {
            data.setImg(imgSelect8.getImage());
            ViewManager.switchTo(Views.HOME);
        }
    }

    @FXML
    void pic9Clicked(MouseEvent event) {

        if(imgSelect9.getImage() != null) {
            data.setImg(imgSelect9.getImage());
            ViewManager.switchTo(Views.HOME);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) { // initialize is called every time this scene is loaded

        // INSERT CODE HERE:
        // Manually insert the pictures from the database into the ImageView objects

    }
}