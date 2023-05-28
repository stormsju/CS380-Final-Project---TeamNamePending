package admin.admingui;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.util.*;
import java.io.*;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static admin.admingui.Activity.*;

/**
 * @author justin Storms
 * @version 1.0 - AdminMainUIController.java
 * @since 5/26/2023
 * Class which controls the behavior for the PicMe Administrator Main UI.
 */
public class AdminMainUIController {
    //current user/log (outputs to logs folder on close/during actions)
    private static AdminUser admin = AdminLoginController.admin;
    private static List<Log> logList = new ArrayList<Log>();
    private static Log log = new Log(admin.getUserName());
    private String logOutputFilePath = //location of log output
                    "/* filepath *.",

            exportOutputFilePath = //location of export output
                    "/* filepath */";


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
        Platform.exit();
        AdminLogin.launch();
    }

    /**
     * Listener event which runs a helper method to export the current query to output file.
     * @param event onClick
     */
    @FXML
    void exportQueryAsFile(MouseEvent event) {
        tfMainOutput.setText("");
        tfSysMessage.setText("");
        //export to queries folder
    }

    /**
     * Listener event which runs a helper method to pull contact IDs using the tfPMUserID data field.
     * @param event onClick
     */
    @FXML
    void submitPullContactIDs(ActionEvent event) {
        tfMainOutput.setText("");
        tfSysMessage.setText("");
        pullContactIDs(Integer.parseInt(tfPMUserID.getText()));
    }

    /**
     * Listener event which runs a helper method to pull PicMe user details using the tfPMUserID data field.
     * @param event onClick
     */
    @FXML
    void submitPullUserDetails(ActionEvent event) {
        tfMainOutput.setText("");
        tfSysMessage.setText("");
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

            logList.add(new Log(log, String.valueOf(picID), DPic, String.valueOf(personID)));
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

            logList.add(new Log(log, String.valueOf(picID), String.valueOf(commentID), DPicC, String.valueOf(personID)));
            tfSysMessage.setText("Comment deletion request on PMUserID: " + personID + ", PictureID: " + picID +
                    " has been successfully processed.");
        } else {
            tfSysMessage.setText("Comment deletion request on PMUserID: " + personID + ", PictureID: " + picID +
                    " has been cancelled.");
        }
    }

    /**
     * Method which prompts the Admin User to delete a post based on a passed PicMe userID. Creates confirmation
     * dialogue to reduce the risk of user error.
     * @param personID PicMeID of an account user.
     * @param postID PostID of the database tuple to be deleted.
     */
    private void deletePost(int personID, int postID){
        //build alert for confirmation
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("WARNING: Delete Post");
        alert.setContentText("Do you wish to delete this post?" +
                "\nPicMe User ID: " + personID +
                "\nPicMe Post ID: " + postID +
                "\nNotice: This process is final and cannot be undone!");
        Optional<ButtonType> confirmation = alert.showAndWait();
        //clear the username text field upon OK
        if(confirmation.get() == ButtonType.OK) {

            /* send delete request to database */

            logList.add(new Log(log, String.valueOf(postID), DPost, String.valueOf(personID)));
            tfSysMessage.setText("Post deletion request on PMUserID: " + personID + " has been successfully processed.");
        } else {
            tfSysMessage.setText("Post deletion request on PMUserID: " + personID + " has been cancelled.");
        }
    }

    /**
     * Method which prompts the Admin User to delete a picture comment based on a passed PicMe userID. Creates
     * confirmation dialogue to reduce the risk of user error.
     * @param personID PicMeID of an account user.
     * @param postID PostID of the database attribute to be set to null.
     * @param commentID CommentID of the database attribute to be set to null.
     */
    private void deletePostComment(int personID, int postID, int commentID){
        //build alert for confirmation
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("WARNING: Delete Post Comment");
        alert.setContentText("Do you wish to delete this comment?" +
                "\nPicMe User ID: " + personID +
                "\nPicMe Post ID: " + postID +
                "\nPicMe Comment ID: " + commentID +
                "\nNotice: This process is final and cannot be undone!");
        Optional<ButtonType> confirmation = alert.showAndWait();
        //clear the username text field upon OK
        if(confirmation.get() == ButtonType.OK) {

            /* send delete request to database */

            logList.add(new Log(log, String.valueOf(postID), String.valueOf(commentID), DPostC, String.valueOf(personID)));
            tfSysMessage.setText("Comment deletion request on PMUserID: " + personID + ", PostID: " + postID +
                    " has been successfully processed.");
        } else {
            tfSysMessage.setText("Comment deletion request on PMUserID: " + personID + ", PostID: " + postID +
                    " has been cancelled.");
        }
    }

    /**
     * Listener event which runs a logic check to determine what query request helper method is being executed.
     * Will exapnd here as it is built.
     * @param event onClick
     */
    @FXML
    void submit(MouseEvent event) {
        tfMainOutput.setText("");
        tfSysMessage.setText("");

        //verify that an entry is present in the PicMe UserID field and that it is a valid ID (numerical only)
        //regex to check for appropriate character type
        Pattern p1 = Pattern.compile("[^a-zA-Z0-9]]"), p2 = Pattern.compile("[a-zA-Z]]");
        Matcher m1 = p1.matcher(tfPMUserID.getText()), m2 = p2.matcher(tfPMUserID.getText());

        boolean containsSpecialCharacter = m1.find(), containsLetter = m2.find();

        if(!containsSpecialCharacter || !containsLetter){
            //create dialog box
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Unable to process PicMe UserID");
            alert.setContentText("The PicMe UserId you have entered must contain only numbers.");
            Optional<ButtonType> confirmation = alert.showAndWait();
            return;
        }

        //submit based on radio selection (this is for btnSubmit only)

        //verify that both PostID and PicID are not being queried at the same time
        if(!(tfPostID.getText().equals("") || tfPostID.getText() == null) &&
                !(tfPicID.getText().equals("") || tfPicID.getText() == null)){

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("WARNING: Too many arguments");
            alert.setContentText("Too many IDs are being used. Please choose *either*:" +
                            "\n1) No PostID or PicID to query user details using PicMe UserID only" +
                            "\n2) PostID to query a post from the PicMe user, or post comments if CommentID is present" +
                            "\n3) PicID to query a picture from the PicMe user, or picture comments if CommentID is present";
            tfSysMessage.setText("Process abandoned, too many arguments in fields.");
            Optional<ButtonType> warning = alert.showAndWait();
            return;
        }

        //if there is a postID query
        if(!(tfPostID.getText().equals("") || tfPostID.getText() == null)){
            String output;
            //check if it is a post comment query
            if(!(tfCommentID.getText().equals("") || tfCommentID.getText() == null)){
                //return the post comment
                pullPostComments(Integer.parseInt(tfPMUserID.getText()), Integer.parseInt(tfPostID.getText()),
                        Integer.parseInt(tfCommentID.getText()));
            } else {
                //return the post
                pullPost(Integer.parseInt(tfPMUserID.getText()), Integer.parseInt(tfPostID.getText()));
            }
            return;
        }

        //if there is a picID query
        if(!(tfPostID.getText().equals("") || tfPostID.getText() == null)){
            String output;
            //check if it is a post comment query
            if(!(tfCommentID.getText().equals("") || tfCommentID.getText() == null)){
                //return the post comment
                pullPictureComments(Integer.parseInt(tfPMUserID.getText()), Integer.parseInt(tfPostID.getText()),
                        Integer.parseInt(tfCommentID.getText()));
            } else {
                //return the post
                pullPicture(Integer.parseInt(tfPMUserID.getText()), Integer.parseInt(tfPostID.getText()));
            }
            return;
        }

        //otherwise pull PicMe User details
        pullUserDetails(Integer.parseInt(tfPMUserID.getText()));
    }

    /**
     * Listener event which runs a helper method to pull PicME user pictures using the tfPMUserID and tfPicID data
     * fields.
     * @param event onClick
     */
    @FXML
    void submitPullPicture(ActionEvent event) {
        tfMainOutput.setText("");
        tfSysMessage.setText("");
        pullPicture(Integer.parseInt(tfPostID.getText()), Integer.parseInt(tfPicID.getText()));
    }

    /**
     * Listener event which runs a helper method to pull PicME user picture comments using the tfPMUserID, tfPicID, and
     * tfCommentID data fields.
     * @param event onClick
     */
    @FXML
    void submitPullPictureComments(ActionEvent event) {
        tfMainOutput.setText("");
        tfSysMessage.setText("");
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
        tfMainOutput.setText("");
        tfSysMessage.setText("");
        pullPost(Integer.parseInt(tfPMUserID.getText()), Integer.parseInt(tfPostID.getText()));
    }

    /**
     * Listener event which runs a helper method to pull PicME user posts using the tfPMUserID, tfPostID, and tfCommentID
     * data fields.
     * @param event onClick
     */
    @FXML
    void submitPullPostComments(ActionEvent event) {
        tfMainOutput.setText("");
        tfSysMessage.setText("");
        pullPostComments(Integer.parseInt(tfPMUserID.getText()), Integer.parseInt(tfPostID.getText()),
                Integer.parseInt(tfCommentID.getText()));
    }


    //Controls
    /**
     * Method which pulls the details of a user by userID.
     * @param personID Database ID of the PicMe user.
     */
    private void pullUserDetails(int personID){
        tfMainOutput.setText("");
        tfSysMessage.setText("");
        //Query on PersonID + PostID retrieving post
        try {
            Person p = Query(personID); //build person profile from query

            tfMainOutput.setText(
                    "Details for PicMe UserID: " + personID +
                    "\n\tUser Name: \t...\t... " + p.getUserName() +
                    "\n\tFirst Name:\t...\t... " + p.getFName() +
                    "\n\tLast Name: \t...\t... " + p.getLName() +
                    "\n\tBirth Date:\t...\t... " + p.getBDate() +
                    "\n\tEmail:     \t...\t... " + p.getEmail()
            );

            logList.add(new Log(log, PUD, String.valueOf(personID)));
        } catch(SQLException e){
            tfMainOutput.setText("No query result found.\nSee System Message below.");
            tfSysMessage.setText(e.getMessage());
        }
    }

    /**
     * Method which pulls the contact IDs of a user by userID.
     * @param personID Database ID of the PicMe user.
     */
    private void pullContactIDs(int personID){
        tfMainOutput.setText("");
        tfSysMessage.setText("");
        //Query on PersonID + PostID retrieving post
        try {
            tfMainOutput.setText("Contact ID list from UserID: " + personID);
            List<Person> contactList = Query(personID).getContactList(); //or replace with a method to extract contacts into a list
            for(Person p : contactList) {
                tfMainOutput.setText(tfMainOutput.getText() + "\n\t" + tfMainOutput.getText());
            }
            logList.add(new Log(log, PCID, String.valueOf(personID)));
        } catch(SQLException e){
            tfMainOutput.setText("No query result found.\nSee System Message below.");
            tfSysMessage.setText(e.getMessage());
        }
    }

    /**
     * Method which pulls a post of a PicMe user by post ID.
     * @param personID Database ID of the PicMe user.
     * @param postID Attribute ID being retrieved.
     */
    private void pullPost(int personID, int postID){
        tfMainOutput.setText("");
        tfSysMessage.setText("");
        //Query on PersonID + PostID retrieving post
        try {
            String str = Query(personID, postID); //should return string from DB
            tfMainOutput.setText(str);
            logList.add(new Log(log, String.valueOf(postID), PPost, String.valueOf(personID)));
        } catch(SQLException e){
            tfMainOutput.setText("No query result found.\nSee System Message below.");
            tfSysMessage.setText(e.getMessage());
        }
    }

    /**
     * Method which pulls a post comment by post and comment ID.
     * @param personID Database ID of the PicMe user.
     * @param postID Attribute ID of the post commented on.
     * @param commentID Attribute ID being retrieved.
     */
    private void pullPostComments(int personID, int postID, int commentID){
        tfMainOutput.setText("");
        tfSysMessage.setText("");
        //Query on PersonID + PostID + CommentID retrieving post
        try {
            String str = Query(personID, postID, commentID); //should return string from DB
            tfMainOutput.setText(str);
            logList.add(new Log(log, String.valueOf(postID), String.valueOf(commentID), PPost, String.valueOf(personID)));
        } catch(SQLException e){
            tfMainOutput.setText("No query result found.\nSee System Message below.");
            tfSysMessage.setText(e.getMessage());
        }
    }

    /**
     * Method which pulls a picture from a PicMe user by post ID.
     * @param personID Database ID of the PicMe user.
     * @param pictureID Attribute ID being retrieved.
     */
    private void pullPicture(int personID, int pictureID){
        tfMainOutput.setText("");
        tfSysMessage.setText("");
        //Query on PersonID + PictureID retrieving post
        try {
            String str = Query(personID, pictureID); //should return string from DB
            tfMainOutput.setText(str);
            logList.add(new Log(log, String.valueOf(pictureID), PPost, String.valueOf(personID)));
        } catch(SQLException e){
            tfMainOutput.setText("No query result found.\nSee System Message below.");
            tfSysMessage.setText(e.getMessage());
        }
    }

    /**
     * Method which pulls a post comment by post and comment ID.
     * @param personID Database ID of the PicMe user.
     * @param pictureID Attribute ID of the picture commented on.
     * @param commentID Attribute ID being retrieved.
     */
    private void pullPictureComments(int personID, int pictureID, int commentID){
        tfMainOutput.setText("");
        tfSysMessage.setText("");
        //Query on PersonID + PictureID + CommentID retrieving post
        try {
            String str = Query(personID, pictureID, commentID); //should return string from DB
            tfMainOutput.setText(str);
            logList.add(new Log(log, String.valueOf(pictureID), String.valueOf(commentID), PPost, String.valueOf(personID)));
        } catch(SQLException e){
            tfMainOutput.setText("No query result found.\nSee System Message below.");
            tfSysMessage.setText(e.getMessage());
        }
    }

    /**
     * Method which clear all UI text fields.
     */
    void clearAll(){
        tfPMUserID.setText("");
        tfPostID.setText("");
        tfPicID.setText("");
        tfCommentID.setText("");
        tfMainOutput.setText("");
        tfSysMessage.setText("");
    }
}