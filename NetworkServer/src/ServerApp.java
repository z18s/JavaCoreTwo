import server_work.MyServer;

public class ServerApp {
    private static final int PORT = 8189;

    public static void main(String[] args) {
        MyServer server = new MyServer(PORT);
        server.start();
    }
}