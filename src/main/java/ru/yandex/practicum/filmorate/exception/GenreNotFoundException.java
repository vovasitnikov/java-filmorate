package ru.yandex.practicum.filmorate.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class GenreNotFoundException  extends RuntimeException {
    public static final String GENRE_NOT_FOUND = "Жанр ID_%d не найден";

    public GenreNotFoundException(String message) {
        super(message);
    }
}