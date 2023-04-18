package ru.yandex.practicum.filmorate.storage.film;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.yandex.practicum.filmorate.model.Film;

import java.util.HashMap;
import java.util.List;

public interface FilmStorage {
    public int getIdFilm();
    public HashMap<Integer, Film> getFilms();

    public List<Film> findAll();

    public Film create(Film film);

    @PutMapping
    public Film update(Film film);

    public Film findFilmById(int id);
}
