package ru.yandex.practicum.filmorate.exception;

public class MpaNotFoundException extends RuntimeException {
    public static final String MPA_NOT_FOUND = "Рейтинг MPA ID_%d не найден";

    public MpaNotFoundException(String message) {
        super(message);
    }
}
