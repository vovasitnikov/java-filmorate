package ru.yandex.practicum.filmorate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.service.FilmService;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/films")
public class FilmController {

    private FilmService filmService;

    @Autowired
    public FilmController(FilmService filmService) {
        this.filmService = filmService;
    }

    @GetMapping
    public List<Film> findAll() {
        return filmService.findAll();
    }

    @GetMapping("/{id}")
    public Film findById(@PathVariable int id) {
        return filmService.findFilmById(id);
    }

    //возвращает список из первых count фильмов по количеству лайков. Если значение параметра count не задано, верните первые 10.
    @GetMapping("/popular")
    public Set<Film> findPopularFilms(@RequestParam(value = "count", defaultValue = "10") Integer count) {
        return filmService.findPopularFilms(count);
    }

    @PostMapping
    public Film create(@RequestBody Film film) {
        return filmService.create(film);
    }

    @PutMapping
    public Film update(@RequestBody Film film) {
        return filmService.update(film);
    }

    //пользователь ставит лайк фильму
    @PutMapping("/{id}/like/{userId}")
    public Film likeToFilm(@PathVariable int id, @PathVariable int userId) {
        return filmService.likeToFilm(id, userId);
    }

    //пользователь удаляет лайк.
    @DeleteMapping("/{id}/like/{userId}")     //удаление
    public Film deleteUserLike(@PathVariable int id, @PathVariable int userId) {
        return filmService.deleteUserLike(id, userId);
    }
}