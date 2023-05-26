package admin.admingui;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;

import java.util.*;
import java.io.*;
import java.sql.SQLException;

public class AdminMainUIController {
    //current user log (outputs to logs folder on close/during actions)
    private List<Log> log = new ArrayList<Log>();

    //GUI Objects
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



    //Event Listeners
    @FXML
    void clearAllFields(MouseEvent event) {
        clearAll();
    }

    @FXML
    void exit(MouseEvent event) {
        Platform.exit();
        System.exit(0);
    }

    void logout(MouseEvent event){
        //show login scene
    }

    @FXML
    void exportQueryAsFile(MouseEvent event) {
        //export to queries folder
    }

    @FXML
    void submitPullContactIDs(ActionEvent event) {
        pullContactIDs(Integer.parseInt(tfPMUserID.getText()));
    }

    @FXML
    void submitPullUserDetails(ActionEvent event) {
        pullUserDetails(Integer.parseInt(tfPMUserID.getText()));
    }

    @FXML
    void submit(MouseEvent event) {
        //submit based on radio selection (this is for btnSubmit only)
    }

    @FXML
    void submitPullPicture(ActionEvent event) {
        pullPicture(Integer.parseInt(tfPostID.getText()), Integer.parseInt(tfPicID.getText()));
    }

    @FXML
    void submitPullPictureComments(ActionEvent event) {
        pullPictureComments(Integer.parseInt(tfPostID.getText()), Integer.parseInt(tfPicID.getText()), Integer.parseInt(tfCommentID.getText()));
    }

    @FXML
    void submitPullPost(ActionEvent event) {
        pullPost(Integer.parseInt(tfPMUserID.getText()), Integer.parseInt(tfPostID.getText()));
    }

    @FXML
    void submitPullPostComments(ActionEvent event) {
        pullPostComments(Integer.parseInt(tfPMUserID.getText()), Integer.parseInt(tfPostID.getText()), Integer.parseInt(tfCommentID.getText()));
    }


    //Controls
    private void pullUserDetails(int personID){

    }

    private void pullContactIDs(int personID){
        //Query on PersonID + PostID retrieving post
        try {
            List<Person> contactList = Query(personID).getContactList();
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
