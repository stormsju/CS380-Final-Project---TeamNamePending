package admin.admingui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

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
        if(tfPassword.equals(""/*Query return result of password based on userID*/)) AdminMainUI.launchUI();
    }

    @FXML
    void newUser(ActionEvent event) {

    }

}
