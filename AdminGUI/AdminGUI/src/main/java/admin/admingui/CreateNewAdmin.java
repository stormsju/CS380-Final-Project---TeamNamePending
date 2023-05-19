package admin.admingui;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class CreateNewAdmin extends Application{
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AdminLogin.class.getResource("CreateNewAdmin.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("New User");
        stage.setScene(scene);
        stage.show();
    }
    public static void launchUI() {
        launch();
    }
}
