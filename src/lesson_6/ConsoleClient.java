package lesson_6;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class ConsoleClient {

    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;
    private Scanner scanner = new Scanner(System.in);
    private boolean status = true;

    Thread threadSend;
    Thread threadReceive;

    public static void main(String[] args) {

        ConsoleClient client = new ConsoleClient();

        try {
            client.openConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openConnection() throws IOException {
        String SERVER_ADDR = "localhost";
        int SERVER_PORT = 8189;

        socket = new Socket(SERVER_ADDR, SERVER_PORT);
        System.out.println("Server connected.");

        in = new DataInputStream(socket.getInputStream());
        out = new DataOutputStream(socket.getOutputStream());

        threadSend = new Thread(this::sendMessage);
        threadReceive = new Thread(this::receiveAnswer);
        threadSend.start();
        threadReceive.start();
    }

    public void exit() {
        closeConnection();
        System.exit(0);
    }

    public void closeConnection() {
        scanner.close();
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
        System.out.println("Server disconnected.");
    }

    private void receiveAnswer() {
        while (status) {
            try {
                String serverAnswer = in.readUTF();
                System.out.println("Server: " + serverAnswer);
            } catch (IOException e) {
                System.out.println("Connection closed.");
                break;
            }
        }
    }

    private void sendMessage() {
        String message;
        while (status) {
            message = scanner.nextLine();
            if (!message.isBlank()) {
                try {
                    out.writeUTF(message);
                } catch (IOException e) {
                    System.out.println("Message output error!");
                }
            }
            if (message.equals("/end")) {
                status = false;
                exit();
                break;
            }
        }
    }
}