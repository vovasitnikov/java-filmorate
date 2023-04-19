package ru.yandex.practicum.filmorate.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends NotFoundException {
    public static final String USER_NOT_FOUND = "Пользователь ID_%d не найден";

    public UserNotFoundException(String message) {
        super(message);
    }
}
