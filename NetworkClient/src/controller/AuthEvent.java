package controller;

@FunctionalInterface
public interface AuthEvent {
    void authIsSuccessful(String nickname);
}