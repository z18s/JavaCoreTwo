package client_work;

import auth.AuthService;
import server_work.MyServer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import static server_work.MessageConstant.*;

public class ClientHandler {

    private final MyServer serverInstance;
    private final AuthService authService;
    private final Socket clientSocket;

    private DataInputStream inputStream;
    private DataOutputStream outputStream;
    private String nickname;


    public ClientHandler(Socket clientSocket, MyServer myServer) {
        this.clientSocket = clientSocket;
        this.serverInstance = myServer;
        this.authService = serverInstance.getAuthService();
    }

    public void handle() throws IOException {
        inputStream = new DataInputStream(clientSocket.getInputStream());
        outputStream = new DataOutputStream(clientSocket.getOutputStream());

        new Thread(() -> {
            try {
                authentication();
                readMessages();
            } catch (IOException e) {
                System.out.println("Connection has been failed.");
            } finally {
                closeConnection();
            }
        }, "Handle Thread").start();
    }

    private void closeConnection() {
        serverInstance.unsubscribe(this);
        try {
            clientSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readMessages() throws IOException {
        while (true) {
            String message = inputStream.readUTF();
            //TODO delete
            System.out.println("inputStream readMes: " + message);
            //*
            if (message.startsWith(END_CMD)) {
                return;
            }
            //TODO delete
            System.out.println(String.format("%s: %s", nickname, message));
            //*
            serverInstance.broadcastMessage(String.format("%s: %s", nickname, message));
        }
    }

    private void authentication() throws IOException {
        while (true) {
            String message = inputStream.readUTF();
            //TODO delete
            System.out.println("inputStream auth: " + message);
            //*
            if (message.startsWith(AUTH_CMD)) {
                String[] parts = message.split("\\s+");
                String login = parts[1];
                String password = parts[2];

                String nickname = authService.getNickByLoginAndPassword(login, password);
                if (nickname == null) {
                    sendMessage("Неверные логин/пароль!");
                }
                else if (serverInstance.isNicknameBusy(nickname)) {
                    sendMessage("Учётная запись уже используется!");
                }
                else {
                    sendMessage(String.format("%s %s", AUTH_SUCCESSFUL_CMD, nickname));
                    setNickname(nickname);
                    serverInstance.broadcastMessage(nickname + " зашёл в чат!");
                    serverInstance.subscribe(this);
                    break;
                }
            }
        }
    }

    private void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getNickname() {
        return nickname;
    }

    public void sendMessage(String message) throws IOException {
        //TODO delete
        System.out.println("sendMessage: " + message);
        //*
        outputStream.writeUTF(message);
    }
}