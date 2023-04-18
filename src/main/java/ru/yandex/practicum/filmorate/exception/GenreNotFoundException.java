package ru.yandex.practicum.filmorate.exception;


public class GenreNotFoundException  extends RuntimeException {
    public static final String GENRE_NOT_FOUND = "Жанр ID_%d не найден";

    public GenreNotFoundException(String message) {
        super(message);
    }
}