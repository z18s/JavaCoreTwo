package controller;

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

public class ChatController implements Initializable {

    private final NetworkService networkService;

    @FXML
    ListView<String> contactsList;

    @FXML
    ListView<String> messagesList;

    @FXML
    TextField messageText;

    public ChatController(NetworkService networkService) {
        this.networkService = networkService;
    }

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

    @FXML
    private void aboutWindow(ActionEvent actionEvent) {
        AppController.aboutWindow(actionEvent);
    }

    @FXML
    private void exit(ActionEvent actionEvent) {
        AppController.exit();
    }
//    public void selectContact(MouseEvent mouseEvent) {
//        partnerName = contactsList.getSelectionModel().getSelectedItem();
//    }

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
            AppController.errorWindow("Failed to send message!");
            e.printStackTrace();
        }
    }

    public void printAnswer(String strFromServer) {
        messagesList.getItems().add(strFromServer);
    }

    private void printOwnMessage(String message) {
        messagesList.getItems().add("Me: " + message);
    }

//    public void chatInit() {
//        contactsListInit();
//        messageText.requestFocus();
//    }

    private void contactsListInit() {
        contactsList.getItems().addAll("User1", "User2", "User3");
    }
}