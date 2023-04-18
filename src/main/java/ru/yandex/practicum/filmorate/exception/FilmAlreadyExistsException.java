package ru.yandex.practicum.filmorate.exception;

public class FilmAlreadyExistsException extends RuntimeException {
    public static final String FILM_ALREADY_EXISTS = "Фильм ID_%d уже существует";

    public FilmAlreadyExistsException(String message) {
        super(message);
    }
}
