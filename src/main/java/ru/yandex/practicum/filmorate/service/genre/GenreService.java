package ru.yandex.practicum.filmorate.service.genre;

import ru.yandex.practicum.filmorate.model.Genre;

public interface GenreService  {

    default Genre add(Genre element) {
        throw new UnsupportedOperationException("Используйте data.sql чтобы добавить новые жанры");
    }

    default Genre update(Genre element) {
        throw new UnsupportedOperationException("Используйте data.sql чтобы обновить жанр");
    }
}
