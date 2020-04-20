package controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import model.NetworkService;
import view.ChatWindow;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AppController implements Initializable {

    public static NetworkService networkService;

    private AuthController authController;
    private ChatController chatController;

    private String nickname;

    public static boolean authOk = false;

    private final String HOST = "localhost";
    private final int PORT = 8189;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        networkService = new NetworkService(HOST, PORT, this);

        try {
            runApplication();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void runApplication() throws IOException {
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
            AppController.this.setUserName(nickname);
            AppController.this.openChat();
        });
    }

    private void openChat() {
        switchToChatWindow();

        chatController = new ChatController(networkService);
        networkService.setMessageHandler(message -> chatController.printAnswer(message));

//        chatController.chatInit();
    }

    private void setUserName(String nickname) {
        this.nickname = nickname;
    }

    public void shutdown() {
        networkService.close();
    }

    public String getUsername() {
        return nickname;
    }

    public void switchToChatWindow() {
        try {
            new ChatWindow().setNickname(getUsername());
        }
        catch (Exception e) {
            System.out.println("Chat Window failed.");
            e.printStackTrace();
        }
    }

    public static void aboutWindow(ActionEvent actionEvent) {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION, "MyChat");
            alert.setTitle("About");
            alert.setHeaderText(null);
            alert.show();
        });
    }

    public static void errorWindow(String text) {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.ERROR, text);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.show();
        });
    }

    public void exit(ActionEvent actionEvent) {
        shutdown();
        exit();
    }

    public static void exit() {
        System.exit(0);
    }
}