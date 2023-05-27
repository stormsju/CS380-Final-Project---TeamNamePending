package admin.admingui;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.util.*;
import java.io.*;
import java.sql.SQLException;

import static admin.admingui.Activity.DUA;

/**
 * @author justin Storms
 * @version 1.0 - AdminMainUIController.java
 * @since 5/26/2023
 * Class which controls the behavior for the PicMe Admin Main UI.
 */
public class AdminMainUIController {
    //current user/log (outputs to logs folder on close/during actions)
    private static AdminUser admin = AdminLoginController.admin;
    private static List<Log> logList = new ArrayList<Log>();
    private static Log log = new Log(admin.getUserName());

    //private listener objects
    @FXML
    private ToggleGroup actionSelection;

    @FXML
    private Button btnClear;

    @FXML
    private Button btnExit;

    @FXML
    private Button btnSubmit;

    @FXML
    private RadioButton radContacts;

    @FXML
    private RadioButton radDelete;

    @FXML
    private RadioButton radQuery;

    @FXML
    private MenuItem menuClearAll;

    @FXML
    private MenuItem menuExit;

    @FXML
    private MenuItem menuExportQueryAsFile;

    @FXML
    private MenuItem menuLogout;

    @FXML
    private MenuItem menuPullContactIDs;

    @FXML
    private MenuItem menuPullPicture;

    @FXML
    private MenuItem menuPullPictureComments;

    @FXML
    private MenuItem menuPullPost;

    @FXML
    private MenuItem menuPullPostComments;

    @FXML
    private MenuItem menuPullUserDetails;

    @FXML
    private TextField tfCommentID;

    @FXML
    private TextArea tfMainOutput;

    @FXML
    private TextField tfOutputFilename;

    @FXML
    private TextField tfPMUserID;

    @FXML
    private TextField tfPicID;

    @FXML
    private TextField tfPostID;

    @FXML
    private TextArea tfSysMessage;



    //Listener events

    /**
     * Listener event which runs a helper method to clear all inputs/outputs from UI.
     * @param event onClick
     */
    @FXML
    void clearAllFields(MouseEvent event) {
        clearAll();
    }

    /**
     * Listener event which closes the Admin UI application.
     * @param event onClick
     */
    @FXML
    void exit(MouseEvent event) {
        Platform.exit();
        System.exit(0);
    }

    /**
     * Listener event which logs the user out of the Admin UI.
     * @param event onClick
     */
    void logout(MouseEvent event){
        //show login scene
    }

    /**
     * Listener event which runs a helper method to export the current query to output file.
     * @param event onClick
     */
    @FXML
    void exportQueryAsFile(MouseEvent event) {
        //export to queries folder
    }

    /**
     * Listener event which runs a helper method to pull contact IDs using the tfPMUserID data field.
     * @param event onClick
     */
    @FXML
    void submitPullContactIDs(ActionEvent event) {
        pullContactIDs(Integer.parseInt(tfPMUserID.getText()));
    }

    /**
     * Listener event which runs a helper method to pull PicMe user details using the tfPMUserID data field.
     * @param event onClick
     */
    @FXML
    void submitPullUserDetails(ActionEvent event) {
        pullUserDetails(Integer.parseInt(tfPMUserID.getText()));
    }

    /**
     * Method which prompts the Admin User to delete an account based on a passed PicMe userID. Creates confirmation
     * dialogue to reduce the risk of user error.
     * @param personID PicMeID of an account user.
     */
    private void deleteUser(int personID){
        //build alert for confirmation
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("WARNING: Delete User Account");
        alert.setContentText("Do you wish to delete this account?" +
                            "\nPicMe User ID: " + personID +
                            "\nNotice: This process is final and cannot be undone!");
        Optional<ButtonType> confirmation = alert.showAndWait();
        //clear the username text field upon OK
        if(confirmation.get() == ButtonType.OK) {

            /* send delete request to database (delete tuple of person and all related data) */

            //log event
            logList.add(new Log(log, DUA, String.valueOf(personID)));
            tfSysMessage.setText("Account deletion request on PMUserID: " + personID + " has been successfully processed.");
        } else {
            tfSysMessage.setText("Account deletion request on PMUserID: " + personID + " has been cancelled.");
        }
    }

    /**
     * Method which prompts the Admin User to delete a picture based on a passed PicMe userID. Creates confirmation
     * dialogue to reduce the risk of user error.
     * @param personID PicMeID of an account user.
     * @param picID PictureID of the database tuple to be deleted.
     */
    private void deletePicture(int personID, int picID){
        //build alert for confirmation
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("WARNING: Delete Picture");
        alert.setContentText("Do you wish to delete this picture?" +
                "\nPicMe User ID: " + personID +
                "\nPicMe Picture ID: " + picID +
                "\nNotice: This process is final and cannot be undone!");
        Optional<ButtonType> confirmation = alert.showAndWait();
        //clear the username text field upon OK
        if(confirmation.get() == ButtonType.OK) {

            /* send delete request to database */

            tfSysMessage.setText("Picture deletion request on PMUserID: " + personID + " has been successfully processed.");
        } else {
            tfSysMessage.setText("Picture deletion request on PMUserID: " + personID + " has been cancelled.");
        }
    }

    /**
     * Method which prompts the Admin User to delete a picture comment based on a passed PicMe userID. Creates
     * confirmation dialogue to reduce the risk of user error.
     * @param personID PicMeID of an account user.
     * @param picID PictureID of the database attribute to be set to null.
     * @param commentID CommentID of the database attribute to be set to null.
     */
    private void deletePictureComment(int personID, int picID, int commentID){
        //build alert for confirmation
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("WARNING: Delete Picture Comment");
        alert.setContentText("Do you wish to delete this comment?" +
                "\nPicMe User ID: " + personID +
                "\nPicMe Picture ID: " + picID +
                "\nPicMe Comment ID: " + commentID +
                "\nNotice: This process is final and cannot be undone!");
        Optional<ButtonType> confirmation = alert.showAndWait();
        //clear the username text field upon OK
        if(confirmation.get() == ButtonType.OK) {

            /* send delete request to database */

            tfSysMessage.setText("Comment deletion request on PMUserID: " + personID + ", PictureID: " + picID +
                    " has been successfully processed.");
        } else {
            tfSysMessage.setText("Comment deletion request on PMUserID: " + personID + ", PictureID: " + picID +
                    " has been cancelled.");
        }
    }

    private void deletePost(int personID, int postID){

    }

    private void deletePostComment(int personID, int postID, int commentID){

    }

    /**
     * Listener event which runs a logic check to determine what query request helper method is being executed.
     * Will exapnd here as it is built.
     * @param event onClick
     */
    @FXML
    void submit(MouseEvent event) {
        //submit based on radio selection (this is for btnSubmit only)
    }

    /**
     * Listener event which runs a helper method to pull PicME user pictures using the tfPMUserID and tfPicID data
     * fields.
     * @param event onClick
     */
    @FXML
    void submitPullPicture(ActionEvent event) {
        pullPicture(Integer.parseInt(tfPostID.getText()), Integer.parseInt(tfPicID.getText()));
    }

    /**
     * Listener event which runs a helper method to pull PicME user picture comments using the tfPMUserID, tfPicID, and
     * tfCommentID data fields.
     * @param event onClick
     */
    @FXML
    void submitPullPictureComments(ActionEvent event) {
        pullPictureComments(Integer.parseInt(tfPostID.getText()), Integer.parseInt(tfPicID.getText()),
                Integer.parseInt(tfCommentID.getText()));
    }

    /**
     * Listener event which runs a helper method to pull PicME user posts using the tfPMUserID and tfPostID data
     * fields.
     * @param event onClick
     */
    @FXML
    void submitPullPost(ActionEvent event) {
        pullPost(Integer.parseInt(tfPMUserID.getText()), Integer.parseInt(tfPostID.getText()));
    }

    /**
     * Listener event which runs a helper method to pull PicME user posts using the tfPMUserID, tfPostID, and tfCommentID
     * data fields.
     * @param event onClick
     */
    @FXML
    void submitPullPostComments(ActionEvent event) {
        pullPostComments(Integer.parseInt(tfPMUserID.getText()), Integer.parseInt(tfPostID.getText()),
                Integer.parseInt(tfCommentID.getText()));
    }


    //Controls

    private void pullUserDetails(int personID){

    }

    private void pullContactIDs(int personID){
        //Query on PersonID + PostID retrieving post
        try {
            List<Person> contactList = Query(personID).getContactList(); //or replace wit a method to extract contacts into a list
            for(Person p : contactList) {
                tfMainOutput.setText("" + tfMainOutput.getText() + str + "\n");
            }
        } catch(SQLException e){
            tfMainOutput.setText("No query result found.\nSee System Message below.");
            tfSysMessage.setText(e.getMessage());
        }
    }

    private void pullPost(int personID, int postID){
        //Query on PersonID + PostID retrieving post
        try {
            String str = Query(personID); //should return string from DB
            tfMainOutput.setText(str);
        } catch(SQLException e){
            tfMainOutput.setText("No query result found.\nSee System Message below.");
            tfSysMessage.setText(e.getMessage());
        }
    }

    private void pullPostComments(int personID, int postID, int commentID){

    }

    private void pullPicture(int personID, int pictureID){

    }

    private void pullPictureComments(int personID, int pictureID, int commentID){

    }

    void clearAll(){
        tfPMUserID.setText("");
        tfPostID.setText("");
        tfPicID.setText("");
        tfCommentID.setText("");
        tfMainOutput.setText("");
        tfSysMessage.setText("");
    }

}
