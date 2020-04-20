package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import model.NetworkService;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AuthController implements Initializable {

    private final NetworkService networkService;

    @FXML
    TextField loginText;

    @FXML
    PasswordField passwordText;

    @FXML
    Label loginStatus;

    public AuthController(NetworkService networkService) {
        this.networkService = networkService;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

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

    @FXML
    private void aboutWindow(ActionEvent actionEvent) {
        AppController.aboutWindow(actionEvent);
    }

    @FXML
    private void exit(ActionEvent actionEvent) {
        AppController.exit();
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
            AppController.errorWindow("Authentication error!");
        }
    }
}