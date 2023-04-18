package ru.yandex.practicum.filmorate.storage.dao.film;

import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.model.Genre;
import ru.yandex.practicum.filmorate.model.User;

import java.util.Collection;
import java.util.Set;

public interface FilmStorage {

    public Film add(Film film);

    Film update(Film film);

    Film get(long userID);

    Collection<Film> getAll();

    boolean contains(long userID);

    void addGenres(long filmID, Set<Genre> genres);

    void updateGenres(long filmID, Set<Genre> genres);

    Set<Genre> getGenres(long filmID);
}
