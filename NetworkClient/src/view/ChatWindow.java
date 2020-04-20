package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;

public class ChatWindow {
    private FXMLLoader chatFxml;
    private String nickname;

    public ChatWindow() throws IOException {
        chatFxml = new FXMLLoader(getClass().getResource("chat.fxml"));
        Parent chat = chatFxml.load();
        AppScene chatScene = new AppScene(chat);

        AppScene.stage.setScene(chatScene);
        AppScene.stage.setTitle("MyChat - " + nickname);
        AppScene.stage.show();
    }

//    public ChatWindow(Stage stage, int w, int h) throws IOException {
//        Parent chat = FXMLLoader.load(getClass().getResource("chat.fxml"));
//        AppScene chatScene = new AppScene(chat, w, h);
//        stage.setScene(chatScene);
//        stage.setTitle("MyChat - " + nickname);
//        stage.show();
//    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}