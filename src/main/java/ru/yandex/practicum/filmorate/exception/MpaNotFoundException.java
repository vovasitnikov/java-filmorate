package ru.yandex.practicum.filmorate.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MpaNotFoundException extends RuntimeException {
    public static final String MPA_NOT_FOUND = "Рейтинг MPA ID_%d не найден";

    public MpaNotFoundException(String message) {
        super(message);
    }
}
