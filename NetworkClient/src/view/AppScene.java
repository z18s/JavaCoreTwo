package view;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AppScene extends Scene {

    public static Stage stage;
    public static int WIDTH;
    public static int HEIGHT;

    public AppScene(Parent root, int w, int h) {
        super(root, w, h);
        this.getStylesheets().add(getClass().getResource("/view/styles.css").toExternalForm());
    }
}