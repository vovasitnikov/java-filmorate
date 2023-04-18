package ru.yandex.practicum.filmorate.exception;

public class UserAlreadyExistsException extends RuntimeException {
    public static final String USER_ALREADY_EXISTS = "Пользователь ID_%d уже существует";

    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
