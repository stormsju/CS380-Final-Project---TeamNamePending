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

    public void setImgSelect1(ImageView imgSelect1) {
        this.imgSelect1 = imgSelect1;
    }

    public void setImgSelect2(ImageView imgSelect2) {
        this.imgSelect2 = imgSelect2;
    }

    public void setImgSelect3(ImageView imgSelect3) {
        this.imgSelect3 = imgSelect3;
    }

    public void setImgSelect4(ImageView imgSelect4) {
        this.imgSelect4 = imgSelect4;
    }

    public void setImgSelect5(ImageView imgSelect5) {
        this.imgSelect5 = imgSelect5;
    }

    public void setImgSelect6(ImageView imgSelect6) {
        this.imgSelect6 = imgSelect6;
    }

    public void setImgSelect7(ImageView imgSelect7) {
        this.imgSelect7 = imgSelect7;
    }

    public void setImgSelect8(ImageView imgSelect8) {
        this.imgSelect8 = imgSelect8;
    }

    public void setImgSelect9(ImageView imgSelect9) {
        this.imgSelect9 = imgSelect9;
    }

    @FXML
    void homeNavButtonPressed(ActionEvent event) {
        PicMeGUI.pss.setImgSelect1(this.imgSelect1);
        PicMeGUI.pss.setImgSelect2(this.imgSelect2);
        PicMeGUI.pss.setImgSelect3(this.imgSelect3);
        PicMeGUI.pss.setImgSelect4(this.imgSelect4);
        PicMeGUI.pss.setImgSelect5(this.imgSelect5);
        PicMeGUI.pss.setImgSelect6(this.imgSelect6);
        PicMeGUI.pss.setImgSelect7(this.imgSelect7);
        PicMeGUI.pss.setImgSelect8(this.imgSelect8);
        PicMeGUI.pss.setImgSelect9(this.imgSelect9);
        HomeController hc = new HomeController();
        hc.setImgFeed(PicMeGUI.hs.getImgFeed());
        hc.setTfCaption(PicMeGUI.hs.getTfCaption());
        hc.homeNavButtonPressed();

    }

    @FXML
    public void photoNavButtonPressed() {
        PicMeGUI.pss.setImgSelect1(this.imgSelect1);
        PicMeGUI.pss.setImgSelect2(this.imgSelect2);
        PicMeGUI.pss.setImgSelect3(this.imgSelect3);
        PicMeGUI.pss.setImgSelect4(this.imgSelect4);
        PicMeGUI.pss.setImgSelect5(this.imgSelect5);
        PicMeGUI.pss.setImgSelect6(this.imgSelect6);
        PicMeGUI.pss.setImgSelect7(this.imgSelect7);
        PicMeGUI.pss.setImgSelect8(this.imgSelect8);
        PicMeGUI.pss.setImgSelect9(this.imgSelect9);
        // INSERT CODE HERE:
        // Fill the 3 x 3 grid with images from posts from the database.
        // There are 9 available spots.
        // To change the first spot, imgSelect1.setImage(imageFile:string)
        // To change second spot, imgSelect2.setImage(imageFile:string)
        // and so on until ninth spot, imgSelect9.setImage(imageFile:string)
        ViewManager.switchTo(Views.PICTURESELECT);
        this.imgSelect1 = PicMeGUI.pss.getImgSelect1();
        this.imgSelect2 = PicMeGUI.pss.getImgSelect2();
        this.imgSelect3 = PicMeGUI.pss.getImgSelect3();
        this.imgSelect4 = PicMeGUI.pss.getImgSelect4();
        this.imgSelect5 = PicMeGUI.pss.getImgSelect5();
        this.imgSelect6 = PicMeGUI.pss.getImgSelect6();
        this.imgSelect7 = PicMeGUI.pss.getImgSelect7();
        this.imgSelect8 = PicMeGUI.pss.getImgSelect8();
        this.imgSelect9 = PicMeGUI.pss.getImgSelect9();

    }

    @FXML
    void profileNavButtonPressed(ActionEvent event) {
        PicMeGUI.pss.setImgSelect1(this.imgSelect1);
        PicMeGUI.pss.setImgSelect2(this.imgSelect2);
        PicMeGUI.pss.setImgSelect3(this.imgSelect3);
        PicMeGUI.pss.setImgSelect4(this.imgSelect4);
        PicMeGUI.pss.setImgSelect5(this.imgSelect5);
        PicMeGUI.pss.setImgSelect6(this.imgSelect6);
        PicMeGUI.pss.setImgSelect7(this.imgSelect7);
        PicMeGUI.pss.setImgSelect8(this.imgSelect8);
        PicMeGUI.pss.setImgSelect9(this.imgSelect9);
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