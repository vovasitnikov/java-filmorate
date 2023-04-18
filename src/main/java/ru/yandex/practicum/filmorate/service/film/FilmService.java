package ru.yandex.practicum.filmorate.service.film;

import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.service.Service;

import java.util.Collection;

public interface FilmService extends Service<Film> {


    Collection<Film> getPopularFilms(int count);

    void addLike(long filmID, long userID);

    void deleteLike(long filmID, long userID);
}
