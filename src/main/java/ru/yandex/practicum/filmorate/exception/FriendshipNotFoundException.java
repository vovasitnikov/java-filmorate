package ru.yandex.practicum.filmorate.exception;

public class FriendshipNotFoundException extends NotFoundException {
    public static final String FRIENDSHIP_NOT_FOUND =
            "Запрос на дружбу от пользователя ID_%d к пользователю ID_%d не найден";

    public FriendshipNotFoundException(String message) {
        super(message);
    }
}
