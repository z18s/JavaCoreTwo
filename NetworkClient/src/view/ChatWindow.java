package view;

import controller.AppController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;

public class ChatWindow {
    private final AppScene scene;
    private final AppController controller;

    private String nickname;

    public ChatWindow() throws IOException {
        FXMLLoader chatFxml = new FXMLLoader(getClass().getResource("/view/chat.fxml"));
        Parent chat = chatFxml.load();
        scene = new AppScene(chat);
        controller = chatFxml.getController();
    }

    public void init() {
        AppScene.stage.setScene(scene);
        AppScene.stage.setTitle("MyChat - " + nickname);
        AppScene.stage.show();
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public AppController getController() {
        return controller;
    }
}