package ru.yandex.practicum.filmorate.exception;

public class UserNotFoundException extends NotFoundException {
    public static final String USER_NOT_FOUND = "Пользователь ID_%d не найден";

    public UserNotFoundException(String message) {
        super(message);
    }
}
