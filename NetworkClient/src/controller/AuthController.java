package controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import logic.Command;
import model.NetworkService;
import view.ChatWindow;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AuthController extends AppController implements Initializable {

    private ChatWindow chatWindow;
    private volatile boolean chatPainted;

    @FXML
    TextField loginText;

    @FXML
    PasswordField passwordText;

    @FXML
    Label loginStatus;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        String HOST = "localhost";
        int PORT = 8189;

        networkService = new NetworkService(HOST, PORT, this);

        runApplication();
    }

    @FXML
    private void authenticationWithButton(ActionEvent actionEvent) {
        authentication();
    }

    @FXML
    private void authenticationWithEnter(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            authentication();
        }
    }

    @FXML
    private void switchToPasswordFieldWithEnter(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            passwordText.requestFocus();
        }
    }

    public void sendAuthMessage(String login, String pass) {
        sendCommand(Command.authCommand(login, pass));
    }

    private void authentication() {
        String login = loginText.getText();
        String password = passwordText.getText();
        loginStatus.setText("Checking...");
        sendAuthMessage(login, password);
    }

    private void runApplication() {
        connectToServer();
        runAuthProcess();
    }

    private void connectToServer() {
        try {
            networkService.connect();
        } catch (IOException e) {
            System.err.println("Failed to establish server connection.");
        }
    }

    private void runAuthProcess() {
        networkService.setSuccessfulAuthEvent(nickname -> {
            AuthController.this.setUserName(nickname);
            AuthController.this.openChat();
        });
    }

    private void openChat() {
        Platform.runLater(() -> paintChat());

        while (!chatPainted) Thread.onSpinWait();

        networkService.setChatWindow(chatWindow);

        chatWindow.setNickname(nickname);
        networkService.setMessageHandler(((ChatController)chatWindow.getController())::printAnswer);

        Platform.runLater(() -> chatWindow.init());
    }

    private void paintChat() {
        try {
            chatWindow = new ChatWindow();
        } catch (IOException e) {
            System.err.println("Chat Window failed.");
            e.printStackTrace();
        }
        chatPainted = true;
    }

    private void setUserName(String nickname) {
        AppController.nickname = nickname;
    }

    public void shutdown() {
        networkService.close();
    }
}