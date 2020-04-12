package lesson_4;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    private String partnerName;

    @FXML
    ListView<String> contactsList;

    @FXML
    ListView<String> messagesList;

    @FXML
    TextField messageText;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        contactsList.getItems().addAll("John", "Anna", "Tom");
        messageText.requestFocus();
    }

    private void sendMessage() {
        if (!messageText.getText().isBlank()) {
            printMessage();
            //---
            printAnswer();
        }
    }

    private void printMessage() {
        messagesList.getItems().add("Me: " + messageText.getText());
        messageText.clear();
    }

//    private void receiveAnswer() {}

    private void printAnswer() {
        if (partnerName != null) {
            messagesList.getItems().add(partnerName + ": ...");
        }
    }

    public void sendMessageWithButton(ActionEvent actionEvent) {
        sendMessage();
    }

    public void sendMessageWithEnter(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            sendMessage();
        }
    }

    public void aboutWindow(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "MyChat");
        alert.setTitle("About");
        alert.setHeaderText(null);
        alert.show();
    }

    public void exit(ActionEvent actionEvent) {
        Platform.exit();
    }

    public void selectContact(MouseEvent mouseEvent) {
        partnerName = contactsList.getSelectionModel().getSelectedItem();
    }
}