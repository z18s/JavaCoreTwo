package lesson_6;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ConsoleServer {

    private ServerSocket serverSocket;
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private Scanner scanner = new Scanner(System.in);
    private boolean status = true;

    Thread threadSend;
    Thread threadReceive;

    public static void main(String[] args) {

        ConsoleServer server = new ConsoleServer();

        try {
            server.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openConnection() throws IOException {
        int SERVER_PORT = 8189;

        serverSocket = new ServerSocket(SERVER_PORT);
        System.out.println("Server started.");

        socket = serverSocket.accept();
        System.out.println("Client connected.");

        in = new DataInputStream(socket.getInputStream());
        out = new DataOutputStream(socket.getOutputStream());

        threadSend = new Thread(this::sendMessage);
        threadReceive = new Thread(this::receiveAnswer);
        threadSend.start();
        threadReceive.start();
    }

//    public void reset() {
//        closeConnection();
//        try {
//            openConnection();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        status = true;
//    }

    public void exit() {
        closeConnection();
        System.out.println("Server closed.");
        System.exit(0);
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
        try {
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Client disconnected.");
    }

    private void receiveAnswer() {
        while (status) {
            String clientAnswer = "";
            try {
                clientAnswer = in.readUTF();
                if (clientAnswer.equals("/end")) {
                    status = false;
                    exit();
                    break;
                }
            } catch (EOFException e) {
                System.out.println("Connection closed by client.");
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println("Client: " + clientAnswer);
        }

    }

    private void sendMessage() {
        String message;
        while (status) {
            message = scanner.nextLine();
            if (!message.isBlank() ) {
                try {
                    out.writeUTF(message);
                } catch (IOException e) {
                    System.out.println("Message output error!");
                }
            }
        }
    }
}