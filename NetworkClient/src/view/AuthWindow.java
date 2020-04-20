package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;

public class AuthWindow {

    public AuthWindow() throws IOException {
        Parent auth = FXMLLoader.load(getClass().getResource("auth.fxml"));
        AppScene authScene = new AppScene(auth, AppScene.WIDTH, AppScene.HEIGHT);
        AppScene.stage.setScene(authScene);
        AppScene.stage.setTitle("MyChat - Authentication");
        AppScene.stage.show();
    }

//    public AuthWindow(Stage stage, int w, int h) throws IOException {
//        Parent auth = FXMLLoader.load(getClass().getResource("auth.fxml"));
//        AppScene authScene = new AppScene(auth, w, h);
//        stage.setScene(authScene);
//        stage.setTitle("MyChat - Authentication");
//        stage.show();
//    }
}