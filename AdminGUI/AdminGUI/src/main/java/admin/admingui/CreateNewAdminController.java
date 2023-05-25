package admin.admingui;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class which runs a GUI interface allowing a user to create an admin account for the
 * PicMe database.
 */
public class CreateNewAdminController {
    //Private variable fields
    @FXML
    private Button cancelButton;

    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private DialogPane dialogPane;

    @FXML
    private Button submitButton;

    @FXML
    private TextField userNameField;

    /**
     * Method which closes the admin user account creation scene.
     * @param event onClick Close
     */
    @FXML
    void closeBox(MouseEvent event) {
        Stage stage = (Stage) cancelButton.getScene().getWindow();
        stage.close();
    }

    /**
     * Method which validates the credentials submitted for account creation. Uses regex to validate
     * password requirements along with verification that the user entered the password they meant to
     * via confirmation check.
     * @param event onClick Submit
     */
    @FXML
    void submitCredentials(MouseEvent event) {
        List<Person> allUsers = new ArrayList<Person>();
        String userName = userNameField.getText(), pWord = passwordField.getText(),
                confPWord = confirmPasswordField.getText();
        //pseudocode to show what is needed
        for(Entry e : PicMeDatabase){
            Person person = new Person(e);
            allUsers.add(person);
        }

        // 1) verify userName is unique
        for(Person person : allUsers){
            if(userName.equals(person.getUserName())){
                //create dialog box
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Unable to create username");
                alert.setContentText("The username you have chosen is already in the database." +
                        "\nPlease select a new username and try again.");
                Optional<ButtonType> confirmation = alert.showAndWait();
                //clear the username text field upon OK
                if(confirmation.get() == ButtonType.OK) userNameField.setText("");
                return;
            }
        }

        // 2) validate password
        //regex to check for special characters, letters, and number in password
        Pattern p1 = Pattern.compile("[^a-zA-Z0-9]]"), p2 = Pattern.compile("[0-9]]"),
                p3 = Pattern.compile("[a-zA-Z]]");
        Matcher m1 = p1.matcher(passwordField.getText()), m2 = p2.matcher(passwordField.getText()),
                m3 = p3.matcher(passwordField.getText());
        boolean containsSpecialCharacter = m1.find(), containsDigit = m2.find(),
                containsLetter = m3.find();

        if(!containsSpecialCharacter || !containsDigit || !containsLetter){
            //create dialog box
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Unable to create password");
            alert.setContentText("The password you have selected does not contain a password requirements." +
                    "Please try again.");
            Optional<ButtonType> confirmation = alert.showAndWait();
            //clear the password text fields upon OK
            if(confirmation.get() == ButtonType.OK) {
                passwordField.setText("");
                confirmPasswordField.setText("");
                return;
            }
        }

        //check that both password and confirmation fields match
        if(!passwordField.getText().equals(confirmPasswordField.getText())){
            //create dialog box
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Unable to create password");
            alert.setContentText("The password you have selected does not match. Please try again.");
            Optional<ButtonType> confirmation = alert.showAndWait();
            //clear the password text fields upon OK
            if(confirmation.get() == ButtonType.OK) {
                passwordField.setText("");
                confirmPasswordField.setText("");
                return;
            }
        }

        // 3) on full success send success message and stage.close()
        //create new admin user
        AdminUser user = new AdminUser(userName, pword);
        // ***Output admin user to database***

        //create dialog box
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("New Admin User created!");
        alert.setContentText("Admin User created! successfully!");
        Optional<ButtonType> confirmation = alert.showAndWait();
        //clear the password text fields upon OK
        if(confirmation.get() == ButtonType.OK) {
            usernameField.setText("");
            passwordField.setText("");
            confirmPasswordField.setText("");

        }
        this.stage.close();
        AdminLogin.launch();
    }

}