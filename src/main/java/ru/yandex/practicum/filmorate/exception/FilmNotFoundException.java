package ru.yandex.practicum.filmorate.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class FilmNotFoundException extends NotFoundException {
    public static final String FILM_NOT_FOUND = "Фильм ID_%d не найден";

    public FilmNotFoundException(String message) {
        super(message);
    }
}
