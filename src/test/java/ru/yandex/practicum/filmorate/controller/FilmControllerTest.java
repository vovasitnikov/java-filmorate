package ru.yandex.practicum.filmorate.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.filmorate.model.Film;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class FilmControllerTest {

    private Film film;
    private Map<Integer, Film> mapListFilm;

    @BeforeEach
    void load() {
        LocalDate releaseDate = LocalDate.of(1985, 9, 12);
        film = new Film(1, "Avatar", releaseDate, "Film", 10);
        mapListFilm = new HashMap<>();
    }

    @Test
    void findAll() {
        mapListFilm.put(film.getId(), film);
        assertEquals(1, mapListFilm.size());
    }

    @Test
    void create() {
        mapListFilm.put(film.getId(), film);
        Film savedFilm = mapListFilm.get(1);
        assertEquals(film, savedFilm);
    }

    @Test
    void update() {
        mapListFilm.put(film.getId(), film);
        Film savedFilm = mapListFilm.get(1);
        savedFilm.setName("Sergey");
        mapListFilm.put(savedFilm.getId(), savedFilm);
        assertEquals(savedFilm, mapListFilm.get(1));
    }
}