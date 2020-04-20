package controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import model.NetworkService;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ChatController extends AppController implements Initializable {

    public static NetworkService networkService = AuthController.networkService;

    @FXML
    ListView<String> contactsList;

    @FXML
    ListView<String> messagesList;

    @FXML
    TextField messageText;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        contactsListInit();
        messageText.requestFocus();
    }

    @FXML
    private void sendMessageWithButton(ActionEvent actionEvent) {
        sendMessage();
    }

    @FXML
    private void sendMessageWithEnter(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            sendMessage();
        }
    }

    private void sendMessage() {
        String message = messageText.getText();
        if (!message.isBlank()) {
            sendMessage(message);
            printOwnMessage(message);
            messageText.clear();
        }
    }

    public void sendMessage(String message) {
        try {
            networkService.sendMessage(message);
        } catch (IOException e) {
            errorWindow("Failed to send message!");
            e.printStackTrace();
        }
    }

    public void printAnswer(String strFromServer) {
        Platform.runLater(() -> messagesList.getItems().add(strFromServer));

    }

    private void printOwnMessage(String message) {
        Platform.runLater(() -> messagesList.getItems().add("Me: " + message));
    }

    private void contactsListInit() {
        Platform.runLater(() -> contactsList.getItems().addAll("User1", "User2", "User3"));
    }
}