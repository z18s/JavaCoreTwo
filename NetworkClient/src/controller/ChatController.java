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
import logic.Command;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class ChatController extends AppController implements Initializable {

    private String receiver = "";

    @FXML
    ListView<String> contactsList;

    @FXML
    ListView<String> messagesList;

    @FXML
    TextField messageText;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
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

    @FXML
    private void selectContact(MouseEvent mouseEvent) {
        if (contactsList.getSelectionModel().getSelectedItem().equals(receiver)) {
            Platform.runLater(() -> contactsList.getSelectionModel().clearSelection());
            receiver = "";
        } else {
            receiver = contactsList.getSelectionModel().getSelectedItem();
        }
    }

    private boolean hasSelectedContact() {
        return contactsList.getSelectionModel().getSelectedIndex() != -1;
    }

    private void sendMessage() {
        String message = messageText.getText();
        if (!message.isBlank()) {
            sendMessage(receiver, message);
            printOwnMessage(message);
            messageText.clear();
        }
    }

    public void sendMessage(String username, String message) {
        if (hasSelectedContact()) {
            sendPrivateMessage(username, message);
        } else {
            sendBroadcastMessage(message);
        }
    }

    public void sendBroadcastMessage(String message) {
        sendCommand(Command.broadcastMessageCommand(message));
    }

    public void sendPrivateMessage(String username, String message) {
        sendCommand(Command.privateMessageCommand(username, message));
    }

    public boolean isOwnMessage(String strFromServer) {
        if (strFromServer == null || strFromServer.length() == 0) {
            return false;
        }
        String[] messageParts = strFromServer.split(":", 2);
        return messageParts[0].equals(nickname);
    }

    public void printAnswer(String strFromServer) {
        if (!isOwnMessage(strFromServer)) {
            Platform.runLater(() -> messagesList.getItems().add(strFromServer));
        }
    }

    private void printOwnMessage(String message) {
        Platform.runLater(() -> messagesList.getItems().add(
                String.format("Me%s: %s", (receiver.equals("") ? "" : (" -> " + receiver)), message)
                )
        );
    }

    @Override
    public void updateUsersList(List<String> users) {
        users.remove(nickname);
        contactsListUpdate(users);
    }

    private void contactsListUpdate(List<String> users) {
        Platform.runLater(() -> {
            contactsList.getItems().clear();
            contactsList.getItems().addAll(users);
        });
    }
}