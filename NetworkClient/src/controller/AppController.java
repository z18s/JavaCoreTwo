package controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import logic.Command;
import model.NetworkService;

import java.io.IOException;

public abstract class AppController {

    static NetworkService networkService;
    //static AppController controller;

    static String nickname;

    @FXML
    private void aboutWindow(ActionEvent actionEvent) {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "MyChat");
            alert.setTitle("About");
            alert.setHeaderText(null);
            alert.show();
        });
    }

    public void errorWindow(String errorText) {
        if (Platform.isAccessibilityActive()) {
            Platform.runLater(() -> {
                Alert alert = new Alert(Alert.AlertType.ERROR, errorText);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.show();
            });
        } else {
            System.err.println(errorText);
        }
    }

    @FXML
    private void exit(ActionEvent actionEvent) {
        System.exit(0);
    }

    void sendCommand(Command command) {
        try {
            networkService.sendCommand(command);
        } catch (IOException e) {
            errorWindow(e.getMessage());
        }
    }
}