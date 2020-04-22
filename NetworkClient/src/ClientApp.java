import javafx.application.Application;
import javafx.stage.Stage;
import view.AppScene;
import view.AuthWindow;

import java.io.IOException;

public class ClientApp extends Application {

    @Override
    public void init() {
        int WIDTH = 600;
        int HEIGHT = 400;

        AppScene.WIDTH = WIDTH;
        AppScene.HEIGHT = HEIGHT;
    }

    @Override
    public void start(Stage primaryStage) {

        AppScene.stage = primaryStage;

        try {
            AuthWindow authWindow = new AuthWindow();
            authWindow.init();
        } catch (IOException e) {
            System.err.println("Auth Window failed.");
            e.printStackTrace();
        }
    }

    @Override
    public void stop() {
        System.exit(0);
    }

    public static void main(String[] args) {
        launch(args);
    }
}