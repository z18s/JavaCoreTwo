package model;

import controller.AppController;
import controller.AuthEvent;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.function.Consumer;

import static server_work.MessageConstant.*;

public class NetworkService {

    private final String host;
    private final int port;
    private final AppController controller;
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;

    private Consumer<String> messageHandler;
    private AuthEvent successfulAuthEvent;
    private String nickname;

    public NetworkService(String host, int port, AppController controller) {
        this.host = host;
        this.port = port;
        this.controller = controller;
    }

    public void connect() throws IOException {
        socket = new Socket(host, port);
        in = new DataInputStream(socket.getInputStream());
        out = new DataOutputStream(socket.getOutputStream());
        receiveMessageThread();
    }

    private void receiveMessageThread() {
        new Thread(() -> {
            while (true) {
                try {
                    String message = in.readUTF();
                    if (message.startsWith(AUTH_SUCCESSFUL_CMD)) {
                        String[] messageParts = message.split("\\s+", 2);
                        nickname = messageParts[1];
                        successfulAuthEvent.authIsSuccessful(nickname);
                    }
                    else if (messageHandler != null) {
                        messageHandler.accept(message);
                    } else {
                        controller.errorWindow(message);
                    }
                } catch (IOException e) {
                    System.out.println("Read thread has been interrupted!");
                    return;
                }
            }
        }, "Read Thread").start();
    }

    public void sendAuthMessage(String login, String password) throws IOException {
        out.writeUTF(String.format("%s %s %s", AUTH_CMD, login, password));
    }

    public void sendMessage(String message) throws IOException {
        out.writeUTF(message);
    }

    public void setMessageHandler(Consumer<String> messageHandler) {
        this.messageHandler = messageHandler;
    }

    public void setSuccessfulAuthEvent(AuthEvent successfulAuthEvent) {
        this.successfulAuthEvent = successfulAuthEvent;
    }

    public void close() {
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}