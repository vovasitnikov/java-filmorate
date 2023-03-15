package ru.yandex.practicum.filmorate.storage;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.yandex.practicum.filmorate.model.Film;

import java.util.HashMap;
import java.util.List;

public interface FilmStorage {
    int idFilm = 0;
    HashMap<Integer, Film> films = new HashMap<>();

    @GetMapping
    public List<Film> findAll();

    @PostMapping
    public Film create(@RequestBody Film film);

    @PutMapping
    public Film update(@RequestBody Film film);

}
