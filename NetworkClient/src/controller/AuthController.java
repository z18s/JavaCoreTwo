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
import model.NetworkService;
import view.ChatWindow;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AuthController extends AppController implements Initializable {

    public static NetworkService networkService;

    private ChatWindow chatWindow;

    private String nickname;

    private volatile boolean chatPainted;

    private final String HOST = "localhost";
    private final int PORT = 8189;

    @FXML
    TextField loginText;

    @FXML
    PasswordField passwordText;

    @FXML
    Label loginStatus;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        networkService = new NetworkService(HOST, PORT, this);

        try {
            runApplication();
        } catch (IOException e) {
            e.printStackTrace();
        }
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

    public void sendAuthMessage(String login, String pass) throws IOException {
        networkService.sendAuthMessage(login, pass);
    }

    private void authentication() {
        String login = loginText.getText();
        String password = passwordText.getText();
        loginStatus.setText("Checking...");
        try {
            sendAuthMessage(login, password);
        } catch (IOException e) {
            errorWindow("Authentication error!");
        }
    }

    private void runApplication() throws IOException {
        connectToServer();
        runAuthProcess();
    }

    private void connectToServer() throws IOException {
        try {
            networkService.connect();
        } catch (IOException e) {
            System.err.println("Failed to establish server connection.");
            throw e;
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

        chatWindow.setNickname(getUsername());
        networkService.setMessageHandler(((ChatController)chatWindow.getController())::printAnswer);

        Platform.runLater(() -> chatWindow.init());
    }

    private void paintChat() {
        try {
            chatWindow = new ChatWindow();
        } catch (IOException e) {
            System.out.println("Chat Window failed.");
            e.printStackTrace();
        }
        chatPainted = true;
    }

    private void setUserName(String nickname) {
        this.nickname = nickname;
    }

    public String getUsername() {
        return nickname;
    }

    public void shutdown() {
        networkService.close();
    }
}