package lesson_6;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class EchoServer {

    private static final int SERVER_PORT = 8189 ;

    public static void main(String[] args) {
        Socket socket;
        try (ServerSocket serverSocket = new ServerSocket(SERVER_PORT)) {
            System.out.println("Server started.");
            socket = serverSocket.accept();
            System.out.println("Client connected.");
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());
            while (true) {
                String str = in.readUTF();
                if (str.equals("/end")) {
                    in.close();
                    out.close();
                    socket.close();
                    break;
                }
                System.out.println("Message: " + str);
                out.writeUTF("Echo: " + str);
            }
            System.out.println("Client disconnected.");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}