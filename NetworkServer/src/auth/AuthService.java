package auth;

public interface AuthService {

    String getNickByLoginAndPassword(String login, String password);

    void start();

    void stop();
}