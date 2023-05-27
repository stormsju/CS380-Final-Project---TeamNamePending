package admin.admingui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminMainUI extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(AdminLogin.class.getResource("AdminMainUI.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Admin");
        stage.setScene(scene);
        stage.show();
    }
    public static void launchUI() { launch(); }
}
