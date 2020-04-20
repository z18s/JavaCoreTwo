import javafx.application.Application;
import javafx.stage.Stage;
import view.AppScene;
import view.AuthWindow;

import java.io.IOException;

public class ClientApp extends Application {

    private final int WIDTH = 600;
    private final int HEIGHT = 400;

    @Override
    public void start(Stage primaryStage) {
        AppScene.stage = primaryStage;
        AppScene.WIDTH = WIDTH;
        AppScene.HEIGHT = HEIGHT;

        try {
            AuthWindow authWindow = new AuthWindow();
            authWindow.init();
        } catch (IOException e) {
            System.out.println("Auth Window failed.");
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