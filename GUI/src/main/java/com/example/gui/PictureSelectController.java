package com.example.gui;

import data.DataSingleton;
import entity.Person;
import entity.Picture;
import http.HttpController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.ResourceBundle;

public class PictureSelectController implements Initializable {
    private static String
            pictureFilePath = System.getProperty("user.dir") + "\\src\\main\\Pictures\\";
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

    private void photoIntake(){

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) { // initialize is called every time this scene is loaded
        // INSERT CODE HERE:
        // Manually insert the pictures from the database into the ImageView objects
        Person p = HttpController.personController(1000); //update all IDs with the profile ID for the test
        List<Integer> pictureIDs = new ArrayList<>();
        List<ImageView> images = new ArrayList<>();
        //add images in order for updating
        images.add(imgSelect1);
        images.add(imgSelect2);
        images.add(imgSelect3);
        images.add(imgSelect4);
        images.add(imgSelect5);
        images.add(imgSelect6);
        images.add(imgSelect7);
        images.add(imgSelect8);
        images.add(imgSelect9);
        /*
         * use id to pull photos and populate
         */
        try {
            List<Picture> pictures = HttpController.pictureController(1000);
            for(Picture pic : pictures){
                //create list of picture IDs to get posts
                pictureIDs.add(pic.getId());

                //String base64 = 'data:image/jpg;base64,'+pic.getImage();

                //save picture to local Pictures folder directory
                ByteArrayOutputStream bos = new ByteArrayOutputStream();
                //convert from base64 JSON string to usable data type
                byte[] decode = Base64.getDecoder().decode(pic.getImage());
                ByteArrayInputStream bis = new ByteArrayInputStream(decode);
                BufferedImage bImage = ImageIO.read(bis);
                ImageIO.write(bImage, "jpg", new File(pictureFilePath + pic.getFile()));
                bis.close();

                //update image list with new file directories
                File file = new File(pictureFilePath + pic.getFile());
                images.get(0).setImage(new Image(file.toURI().toString()));
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
}