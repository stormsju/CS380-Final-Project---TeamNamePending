package admin.admingui;

import admin.entity.*;
import admin.http.*;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.io.*;
import java.sql.Blob;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
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
    private static Person person;
    private static List<Friend> fList = new ArrayList<Friend>();
    private static List<Log> logList = new ArrayList<Log>();
    private static Log log = new Log(admin.getUserName());
    private String root = System.getProperty("user.dir"),
                    logOutputFilePath = //location of log output
                            root + "/AdminGUI/AdminGUI/src/main/java/admin/logs/",
                    exportOutputFilePath = //location of export output
                            root + "/AdminGUI/AdminGUI/src/main/java/admin/exports/",
                    pictureOutputFilePath = //location of export output
                            root + "/AdminGUI/AdminGUI/src/main/java/admin/pictures/";


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
        //writes picture or text output field to file
        if(!validatePMUserIDField()){
            return;
        }

        //ensure that a post or post comment is being queried for export and there is an output present
        if((!tfPostID.getText().equals("") || !(tfPostID.getText() == null)) &&
                (!tfMainOutput.getText().equals("") || !(tfMainOutput.getText() == null))){

            //build list of characters and setup iterator
            List<CharSequence> tBlock = tfMainOutput.getParagraphs();
            Iterator itr = tBlock.iterator();
            //need timestamp for file name uniqueness
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            try{
                //write to file
                BufferedWriter write = new BufferedWriter(new FileWriter(new File(exportOutputFilePath +
                        tfOutputFilename.getText() + ".txt")));

                //loop over entire "paragraph"
                while(itr.hasNext()){
                    CharSequence c = (CharSequence) itr.next();
                    write.append(c);
                    write.newLine();
                }
                //create log and close
                logList.add(new Log(log, tfPostID.getText(), tfCommentID.getText(), QRY, tfPMUserID.getText()));
                write.close();
                return;
            } catch(IOException e){
                tfSysMessage.setText(e.getMessage());
            }
            return;
        } else { //else: if not found, display message in tfSysMessage
            tfSysMessage.setText("No query to output, please check your search and try again.");
        }

        if(!tfPicID.getText().equals("") || !(tfPicID.getText() == null)){
            //pull picture and place in export file
            try {
                ResultSet result = (ResultSet) PictureHttp.getPictureWithId(Integer.parseInt(tfPicID.getText()));
                Blob clob = result.getBlob(2);
                byte[] byteArr = clob.getBytes(1,(int)clob.length());

                FileOutputStream fileOut = new FileOutputStream(pictureOutputFilePath + tfOutputFilename.getText()
                        + ".png");
                fileOut.write(byteArr);

                //create log and close
                logList.add(new Log(log, tfPicID.getText(), tfCommentID.getText(), QRY, tfPMUserID.getText()));
                fileOut.close();
                return;
            } catch(Exception e){
                tfMainOutput.setText("No query result found.\nSee System Message below.");
                tfSysMessage.setText(e.getMessage());
            }
        } else { //else: if not found, display message in tfSysMessage
            tfSysMessage.setText("No query to output, please check your search and try again.");
        }

        //otherwise export user details
        //ensure main output isn't empty for export
        if(!tfMainOutput.getText().equals("") || !(tfMainOutput.getText() == null)){
            //build list of characters and setup iterator
            List<CharSequence> tBlock = tfMainOutput.getParagraphs();
            Iterator itr = tBlock.iterator();
            //need timestamp for file name uniqueness
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
            try{
                //write to file
                BufferedWriter write = new BufferedWriter(new FileWriter(new File(exportOutputFilePath +
                        tfOutputFilename.getText() + ".txt" + ".txt")));

                //loop over entire "paragraph"
                while(itr.hasNext()){
                    CharSequence c = (CharSequence) itr.next();
                    write.append(c);
                    write.newLine();
                }
                //create log and close
                logList.add(new Log(log, "", tfCommentID.getText(), QRY, tfPMUserID.getText()));
                write.close();
            } catch(IOException e){
                tfSysMessage.setText(e.getMessage());
            }
        }

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

    private boolean validatePMUserIDField(){
        boolean isValid = false;

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
            isValid = true;
        }

        return isValid;
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

        //ensure user ID is valid
        if(!validatePMUserIDField()){
            return;
        }

        //radButton = Query
        if(radQuery.isSelected()){
            //submit based on radio selection (this is for btnSubmit only)
            //verify that both PostID and PicID are not being queried at the same time
            if(!(tfPostID.getText().equals("") || tfPostID.getText() == null) &&
                    !(tfPicID.getText().equals("") || tfPicID.getText() == null)){

                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("WARNING: Too many arguments");
                alert.setContentText("Too many IDs are being used. Please choose *either*:" +
                                "\n1) No PostID or PicID to query user details using PicMe UserID only" +
                                "\n2) PostID to query a post from the PicMe user, or post comments if CommentID is present" +
                                "\n3) PicID to query a picture from the PicMe user, or picture comments if CommentID is present");
                tfSysMessage.setText("Process abandoned, too many arguments in fields.");
                Optional<ButtonType> warning = alert.showAndWait();
                return;
            }

            //if there is a postID query
            if(!(tfPostID.getText().equals("") || tfPostID.getText() == null)){
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
            if(!(tfPicID.getText().equals("") || tfPicID.getText() == null)){
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
        } else if(radDelete.isSelected()){ //else: Delete selected
            //verify that both PostID and PicID are not being queried at the same time
            if(!(tfPostID.getText().equals("") || tfPostID.getText() == null) &&
                    !(tfPicID.getText().equals("") || tfPicID.getText() == null)){

                Alert alert = new Alert(Alert.AlertType.WARNING);
                alert.setTitle("WARNING: Too many arguments");
                alert.setContentText("Too many IDs are being used. Please choose *either*:" +
                        "\n1) No PostID or PicID to delete account user with PicMe UserID only" +
                        "\n2) PostID to delete a post from the PicMe user, or post comments if CommentID is present" +
                        "\n3) PicID to delete a picture from the PicMe user, or picture comments if CommentID is present");
                tfSysMessage.setText("Process abandoned, too many arguments in fields.");
                Optional<ButtonType> warning = alert.showAndWait();
                return;
            }

            //if there is a postID delete request
            if(!(tfPostID.getText().equals("") || tfPostID.getText() == null)){
                //check if it is a post comment query
                if(!(tfCommentID.getText().equals("") || tfCommentID.getText() == null)){
                    //return the post comment
                    deletePostComment(Integer.parseInt(tfPMUserID.getText()), Integer.parseInt(tfPostID.getText()),
                            Integer.parseInt(tfCommentID.getText()));
                } else {
                    //return the post
                    deletePost(Integer.parseInt(tfPMUserID.getText()), Integer.parseInt(tfPostID.getText()));
                }
                return;
            }

            //if there is a picID delete request
            if(!(tfPicID.getText().equals("") || tfPicID.getText() == null)){
                //check if it is a post comment query
                if(!(tfCommentID.getText().equals("") || tfCommentID.getText() == null)){
                    //return the post comment
                    deletePictureComment(Integer.parseInt(tfPMUserID.getText()), Integer.parseInt(tfPostID.getText()),
                            Integer.parseInt(tfCommentID.getText()));
                } else {
                    //return the post
                    deletePicture(Integer.parseInt(tfPMUserID.getText()), Integer.parseInt(tfPostID.getText()));
                }
                return;
            }

            //otherwise delete account user by PicMe UserID
            deleteUser(Integer.parseInt(tfPMUserID.getText()));
        } else if (radContacts.isSelected()) { //else: Contacts selected
            pullContactIDs(Integer.parseInt(tfPMUserID.getText()));
        }
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
     * Method which pulls the details of a user by userID using query from http.
     * @param personID Database ID of the PicMe user.
     */
    private void pullUserDetails(int personID){
        tfMainOutput.setText("");
        tfSysMessage.setText("");
        //Query on PersonID + PostID retrieving post
        try {
            person = (Person) PersonHttp.getPersonWithId(personID); //build person profile from query

            tfMainOutput.setText(
                    "Details for PicMe UserID: " + personID +
                    "\n\tUser Name: \t...\t... " + person.getUsername() +
                    "\n\tFirst Name:\t...\t... " + person.getFname() +
                    "\n\tLast Name: \t...\t... " + person.getLname() +
                    "\n\tBirth Date:\t...\t... " + person.getDate() +
                    "\n\tEmail:     \t...\t... " + person.getEmail()
            );

            logList.add(new Log(log, PUD, String.valueOf(personID)));
        } catch(Exception e){
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
            fList = FriendHttp.getAllContactIDs(); //or replace with a method to extract contacts into a list
            for(Friend f : fList) {
                tfMainOutput.setText(tfMainOutput.getText() + "\n\t" + f.getId().getFriendToId());
            }
            logList.add(new Log(log, PCID, String.valueOf(personID)));
        } catch(Exception e){
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
            Post post = PostHttp.getPostWithId(postID); //should return string from DB
            tfMainOutput.setText(((Post) post).getText());
            logList.add(new Log(log, String.valueOf(postID), PPost, String.valueOf(personID)));
        } catch(Exception e){
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
            Comment c = CommentHttp.getCommentById(commentID); //should return string from DB
            tfMainOutput.setText(c.getText());
            logList.add(new Log(log, String.valueOf(postID), String.valueOf(commentID), PPost, String.valueOf(personID)));
        } catch(Exception e){
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
            ResultSet result = (ResultSet) PictureHttp.getPictureWithId(pictureID);
            Blob clob = result.getBlob(2);
            byte[] byteArr = clob.getBytes(1,(int)clob.length());

            FileOutputStream fileOut = new FileOutputStream(pictureOutputFilePath);
            fileOut.write(byteArr);

            logList.add(new Log(log, String.valueOf(pictureID), PPost, String.valueOf(personID)));
            fileOut.close();
        } catch(Exception e){
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
            PostPicture postPicture = PostPictureHttp.getPostPictureWithId(commentID);
            Post post = PostHttp.getPostWithId(postPicture.getPostID());
            Comment c = CommentHttp.getCommentById(post.getId());

            tfMainOutput.setText(c.getText());
            logList.add(new Log(log, String.valueOf(pictureID), String.valueOf(commentID), PPost, String.valueOf(personID)));
        } catch(Exception e){
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