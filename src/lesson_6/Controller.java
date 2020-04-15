package lesson_6;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.Socket;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    private final String SERVER_ADDR = "localhost";
    private final int SERVER_PORT = 8189;
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;

//    private String partnerName;

    @FXML
    ListView<String> contactsList;

    @FXML
    ListView<String> messagesList;

    @FXML
    TextField messageText;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }

//        contactsList.getItems().addAll("John", "Anna", "Tom");

        messageText.requestFocus();
    }

    public void openConnection() throws IOException {
        socket = new Socket(SERVER_ADDR, SERVER_PORT);
        in = new DataInputStream(socket.getInputStream());
        out = new DataOutputStream(socket.getOutputStream());
        new Thread(this::receiveAnswer).start();
    }

    public void closeConnection() {
        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void sendMessage() {
        String message = messageText.getText();
        if (!message.isBlank()) {
            try {
                out.writeUTF(message);
                printMessage();
            } catch (IOException e) {
                e.printStackTrace();
                errorWindow("Message output error!");
            }

        }
    }

    private void printMessage() {
        messagesList.getItems().add("Me: " + messageText.getText());
        messageText.clear();
    }

    private void receiveAnswer() {
        try {
            while (true) {
                String strFromServer = in.readUTF();
                if (strFromServer.equalsIgnoreCase("/end")) {
                    break;
                }
                printAnswer(strFromServer);
            }
        } catch (EOFException e) {
            System.out.println("Connection closed by server.");
        } catch(Exception e){
            e.printStackTrace();
        } finally{
            closeConnection();
        }
    }

    private void printAnswer(String strFromServer) {
        Platform.runLater(() -> messagesList.getItems().add(strFromServer));
    }

    public void sendMessageWithButton(ActionEvent actionEvent) {
        sendMessage();
    }

    public void sendMessageWithEnter(KeyEvent keyEvent) {
        if (keyEvent.getCode() == KeyCode.ENTER) {
            sendMessage();
        }
    }

    public void aboutWindow(ActionEvent actionEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, "MyChat");
        alert.setTitle("About");
        alert.setHeaderText(null);
        alert.show();
    }

    public void errorWindow(String text) {
        Alert alert = new Alert(Alert.AlertType.ERROR, text);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.show();
    }

    public void exit(ActionEvent actionEvent) {
        closeConnection();
        Platform.exit();
    }

//    public void selectContact(MouseEvent mouseEvent) {
//        partnerName = contactsList.getSelectionModel().getSelectedItem();
//    }
}