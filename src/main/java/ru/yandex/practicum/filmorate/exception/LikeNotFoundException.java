package ru.yandex.practicum.filmorate.exception;

public class LikeNotFoundException extends NotFoundException {
    public static final String LIKE_NOT_FOUND = "Лайк фильму ID_%d от пользователя ID_%d не найден";

    public LikeNotFoundException(String message) {
        super(message);
    }
}
