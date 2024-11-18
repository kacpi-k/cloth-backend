package dev.kkoncki.cloth.commons;

public enum ErrorCode {
    USER_NOT_FOUND("User not found"),
    USER_NOT_LOGGED_IN("User is not logged in"),
    WRONG_LOGIN_OR_PASSWORD("Wrong login or password");

    ErrorCode(String message) {
        this.message = message;
    }

    final String message;
}
