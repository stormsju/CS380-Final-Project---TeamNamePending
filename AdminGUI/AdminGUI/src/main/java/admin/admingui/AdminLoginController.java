package admin.admingui;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.util.Optional;

public class AdminLoginController {
    protected static AdminUser admin;
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

    @FXML
    void exit(ActionEvent event) {
        Platform.exit();
        System.exit(0);
    }

    @FXML
    void login(ActionEvent event) {
        // *** Query the user based on user ID and store password in checkPassword ***
        String checkPassword = DBQuery.getPassword(tfUserName.getText());

        if(tfPassword.getText().equals(checkPassword)) {
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

    @FXML
    void newUser(ActionEvent event) {
        Platform.exit();
        CreateNewAdmin.launchUI();
    }

}
