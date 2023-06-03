package com.example.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ProfileController {
    protected static ActionEvent source  = new ActionEvent();

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

    public void setImgFeed(ImageView imgFeed) {
        this.imgFeed = imgFeed;
    }

    public void setTfProfile(Label tfProfile) {
        this.tfProfile = tfProfile;
    }

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
        PicMeGUI.ps.setImgFeed(this.imgFeed);
        PicMeGUI.ps.setTfProfile(this.tfProfile);
        HomeController hc = new HomeController();
        hc.setImgFeed(PicMeGUI.hs.getImgFeed());
        hc.setTfCaption(PicMeGUI.hs.getTfCaption());
        hc.homeNavButtonPressed();

    }

    @FXML
    void photoNavButtonPressed(ActionEvent event) {
        PicMeGUI.ps.setImgFeed(this.imgFeed);
        PicMeGUI.ps.setTfProfile(this.tfProfile);
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
    public void profileNavButtonPressed() {
        PicMeGUI.ps.setImgFeed(this.imgFeed);
        PicMeGUI.ps.setTfProfile(this.tfProfile);
        ViewManager.switchTo(Views.PROFILE);
        this.imgFeed = PicMeGUI.ps.getImgFeed();
        this.tfProfile = PicMeGUI.ps.getTfProfile();
        // INSERT CODE HERE:
        // Get the selected post chosen by the user from the picture selection screen.
        // Put the file for the photo from the post into imgFeed.setImage(imageFile:string)
        // Put the caption from the post into tfCaption.setText(caption:string)
    }
}