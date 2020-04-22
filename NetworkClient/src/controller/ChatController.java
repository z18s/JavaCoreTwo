package controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import model.NetworkService;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ChatController extends AppController implements Initializable {

    public static NetworkService networkService = AuthController.networkService;

    private String partnerName = "";

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
            sendMessage(partnerName, message);
            //printOwnMessage(message);
            messageText.clear();
        }
    }

    public void sendMessage(String nickname, String message) {
        try {
            if (isSelectedContact()) {
                networkService.sendPrivateMessage(nickname, message);
            } else {
                networkService.sendMessage(message);
            }
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
        Platform.runLater(() -> contactsList.getItems().addAll("nickname1", "nickname2", "nickname3"));
    }

    @FXML
    private void selectContact(MouseEvent mouseEvent) {
        if (contactsList.getSelectionModel().getSelectedItem().equals(partnerName)) {
            Platform.runLater(() -> contactsList.getSelectionModel().clearSelection());
            partnerName = "";
        } else {
            partnerName = contactsList.getSelectionModel().getSelectedItem();
        }
    }

    private boolean isSelectedContact() {
        return contactsList.getSelectionModel().getSelectedIndex() != -1;
    }
}