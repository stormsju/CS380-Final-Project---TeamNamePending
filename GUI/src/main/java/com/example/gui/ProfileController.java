package com.example.gui;

import data.DataSingleton;
import entity.Person;
import http.HttpController;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import java.net.URL;
import java.util.ResourceBundle;

public class ProfileController implements Initializable {

    DataSingleton data = DataSingleton.getInstance();

    @FXML
    private ImageView imgFeed;
    @FXML
    private Label tfProfile;
    @FXML
    private Hyperlink tfUsername;
    @FXML
    private Label tfDate;

    @FXML
    void homeNavButtonPressed() {
        ViewManager.switchTo(Views.HOME);
    }

    @FXML
    void photoNavButtonPressed() {
        ViewManager.switchTo(Views.PICTURESELECT);
    }

    private boolean checkIfEmpty(Object o){
        if(o instanceof ImageView){
            if (((ImageView) o).getImage() == null){
                return true;
            }
        }
        if(o instanceof Label){
            if (((Label) o).getText() == null || ((Label) o).getText().equals("")){
                return true;
            }
        }
        if(o instanceof Hyperlink){
            if (((Hyperlink) o).getText() == null || ((Hyperlink) o).getText().equals("")){
                return true;
            }
        }
        return false;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        imgFeed.setImage(data.getImg());
        tfProfile.setText(data.getUserInfo());
        tfUsername.setText(data.getUsername());
        tfDate.setText(data.getDate());
        //pull person by ID from database only if an empty data member is found
        Person p = HttpController.personController(1000); //update all IDs with the profile ID for the test
        if(this.checkIfEmpty(tfUsername) || this.checkIfEmpty(tfDate) || this.checkIfEmpty(tfProfile) ||
                this.checkIfEmpty(imgFeed)) {


            //build all fields that are not already populated from person object
            try {
                if (this.checkIfEmpty(tfUsername)) {
                    tfUsername.setText(p.getUsername());
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }


            try {
                if (this.checkIfEmpty(tfProfile)) {
                    tfProfile.setText(p.toString()); //pull the date of the post
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
            /*
            //date and picture will not update until selected
            try {
                if (this.checkIfEmpty(tfDate)) {
                    tfDate.setText(); //pull the date of the post
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
                e.printStackTrace();
            }
             */

            //we may not handle the main profile photo here, though the IF conditional check exists
        }
    }
}