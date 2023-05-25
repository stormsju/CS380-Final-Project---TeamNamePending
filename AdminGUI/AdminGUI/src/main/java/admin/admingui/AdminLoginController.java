package admin.admingui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.util.Optional;

public class AdminLoginController {

    @FXML
    private PasswordField tfPassword;

    @FXML
    private TextField tfUserName;

    @FXML
    void exit(ActionEvent event) {

    }

    @FXML
    void login(ActionEvent event) {
        // *** Query the user based on user ID and store password in checkPassword ***
        String checkPassword = DBQuery.getPassword(tfUserName.getText());

        if(tfPassword.getTExt().equals(checkPassword)) {
            this.stage.close();
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
        this.stage.close();
        CreateNewAdmin.launchUI();
        return;
    }

}
