package ru.yandex.practicum.filmorate.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class FilmControllerTest {

    private Film film;
    private Map<Integer, Film> mapListFilm;
    private Set<Long> likes = new HashSet<>();

    @BeforeEach
    void load() {
        LocalDate releaseDate = LocalDate.of(1985, 9, 12);
        film = new Film(1, "Avatar", releaseDate, "Film", 10, likes);
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
    void createEmptyName() {
        try {
            film.setName("");
            checkFilm(film);
        } catch (Exception e) {
            assertEquals("Нет названия фильма",
                    e.getMessage());
            return;
        }
        fail("Тест провален");
    }

    @Test
    void createLongDescription() {
        try {
            String description = "";
            for (int i = 0; i < 210; i++) {
                description = description + 1;
            }
            film.setDescription(description);
            checkFilm(film);
        } catch (Exception e) {
            assertEquals("Описание слишком длинное",
                    e.getMessage());
            return;
        }
        fail("Тест провален");
    }

    @Test
    void createEmptyDate() {
        try {
            film.setReleaseDate(null);
            checkFilm(film);
        } catch (Exception e) {
            assertEquals("Дата пустая",
                    e.getMessage());
            return;
        }
        fail("Тест провален");
    }

    @Test
    void createFailDuration() {
        try {
            film.setDuration(-1);
            checkFilm(film);
        } catch (Exception e) {
            assertEquals("Продолжительность меньше нуля",
                    e.getMessage());
            return;
        }
        fail("Тест провален");
    }

    private void checkFilm(Film film) {
        LocalDate bornCinema = LocalDate.of(1895, 12, 28);
        if (film.getId() == 0) film.setId(1);
        if (film.getName().isBlank()) throw new ValidationException("Нет названия фильма");
        if (film.getDescription().length() > 200) throw new ValidationException("Описание слишком длинное");
        if (film.getReleaseDate() == null)  throw new ValidationException("Дата пустая");
        if (film.getReleaseDate().isBefore(bornCinema)) throw new ValidationException("Дата выпуска слишком ранняя");
        if (film.getDuration() < 0) throw new ValidationException("Продолжительность меньше нуля");
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