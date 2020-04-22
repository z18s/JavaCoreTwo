package controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public abstract class AppController {

    @FXML
    private void aboutWindow(ActionEvent actionEvent) {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "MyChat");
            alert.setTitle("About");
            alert.setHeaderText(null);
            alert.show();
        });
    }

    public void errorWindow(String text) {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.ERROR, text);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.show();
        });
    }

    @FXML
    private void exit(ActionEvent actionEvent) {
        System.exit(0);
    }
}