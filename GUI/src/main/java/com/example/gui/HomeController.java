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
    private ImageView imgFeed;

    @FXML
    private Label tfCaption;

    public void setImgFeed(ImageView imgFeed) {
        this.imgFeed = imgFeed;
    }

    public void setTfCaption(Label tfCaption) {
        this.tfCaption = tfCaption;
    }

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
        PicMeGUI.hs.setImgFeed(this.imgFeed);
        PicMeGUI.hs.setTfCaption(this.tfCaption);
        ViewManager.switchTo(Views.HOME);
        this.imgFeed = PicMeGUI.hs.getImgFeed();
        this.tfCaption = PicMeGUI.hs.getTfCaption();
        setPhoto("C:\\Users\\jstor\\OneDrive\\Desktop\\CS380\\Final Project\\GUI\\src\\main\\resources\\alternatePhoto.jpg");
    }

    @FXML
    void photoNavButtonPressed(ActionEvent event) {
        PicMeGUI.hs.setImgFeed(this.imgFeed);
        PicMeGUI.hs.setTfCaption(this.tfCaption);
        PictureSelectController psc = new PictureSelectController();
        psc.setImgSelect1(PicMeGUI.pss.getImgSelect1());
        psc.setImgSelect2(PicMeGUI.pss.getImgSelect2());
        psc.setImgSelect3(PicMeGUI.pss.getImgSelect3());
        psc.setImgSelect4(PicMeGUI.pss.getImgSelect4());
        psc.setImgSelect5(PicMeGUI.pss.getImgSelect5());
        psc.setImgSelect6(PicMeGUI.pss.getImgSelect6());
        psc.setImgSelect7(PicMeGUI.pss.getImgSelect7());
        psc.setImgSelect8(PicMeGUI.pss.getImgSelect8());
        psc.setImgSelect9(PicMeGUI.pss.getImgSelect9());
        psc.photoNavButtonPressed();

    }

    @FXML
    void profileNavButtonPressed(ActionEvent event) {
        PicMeGUI.hs.setImgFeed(this.imgFeed);
        PicMeGUI.hs.setTfCaption(this.tfCaption);
        ProfileController pc = new ProfileController();
        pc.setImgFeed(PicMeGUI.ps.getImgFeed());
        pc.setTfProfile(PicMeGUI.ps.getTfProfile());
        pc.profileNavButtonPressed();
    }
}