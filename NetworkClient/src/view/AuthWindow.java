package view;

import controller.AppController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;

public class AuthWindow {
    private final AppScene scene;
    private final AppController controller;

    public AuthWindow() throws IOException {
        FXMLLoader authFxml = new FXMLLoader(getClass().getResource("/view/auth.fxml"));
        Parent auth = authFxml.load();
        scene = new AppScene(auth);
        controller = authFxml.getController();
    }

    public void init() {
        AppScene.stage.setScene(scene);
        AppScene.stage.setTitle("MyChat - Authentication");
        AppScene.stage.show();
    }

    public AppController getController() {
        return controller;
    }
}