package com.admin.admingui;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.Optional;
/**
 * @author justin Storms
 * @version 1.0 - AdminMainUIController.java
 * @since 5/26/2023
 * Class which controls the behavior for the PicMe Administrator Login UI.
 */
public class AdminLoginController {
    //class variable
    protected static AdminUser admin;


    //action listeners
    @FXML
    private Button btnExit;

    @FXML
    private Button btnLogin;

    @FXML
    private Button btnNewUser;

    @FXML
    private PasswordField tfPassword;

    @FXML
    private TextField tfUserName;


    //event handlers
    /**
     * Method to exit application.
     * @param event onClick
     */
    @FXML
    void exit(ActionEvent event) {
        Platform.exit();
        System.exit(0);
    }

    /**
     * Method which handles verification os login credentials and Main UI launch upon success.
     * @param event onClick
     */
    @FXML
    void login(ActionEvent event) {
        // *** Query the user based on user ID and store password in checkPassword ***
        /*String checkPassword = DBQuery.getPassword(tfUserName.getText());*/

        if(tfPassword.getText().equals(""/*checkPassword*/)) {
            admin = new AdminUser(tfUserName.getText(), tfPassword.getText());
            Platform.exit();
            AdminMainUI.launchUI();
            return;
        }

        //create dialog box
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Unable to verify User");
        alert.setContentText("The username or password you have entered is incorrect.");
        Optional<ButtonType> confirmation = alert.showAndWait();
        //clear the username text field upon OK
        if(confirmation.get() == ButtonType.OK) {
            tfUserName.setText("");
            tfPassword.setText("");
        }
    }

    /**
     * Method which handles New User UI launch for Admin account creation.
     * @param event onClick
     */
    @FXML
    void newUser(ActionEvent event) {
        Platform.exit();
        CreateNewAdmin.launchUI();
    }

}
