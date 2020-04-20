package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;

public class ChatWindow {
    private String nickname;

    public ChatWindow() throws IOException {
        Parent chat = FXMLLoader.load(getClass().getResource("chat.fxml"));
        AppScene authScene = new AppScene(chat, AppScene.WIDTH, AppScene.HEIGHT);
        AppScene.stage.setScene(authScene);
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