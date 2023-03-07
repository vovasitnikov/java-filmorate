package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/films")
@Slf4j
public class FilmController {

    private List<Film> films = new ArrayList<>();

    @GetMapping
    public List<Film> findAll() {
        return films;
    }

    @PostMapping
    public Film create(@RequestBody Film film) throws ValidationException {
        checkFilm(film);
        films.add(film);
        log.info("Получен запрос на создание нового фильма");
        return film;
    }

    private void checkFilm(Film film) throws ValidationException {
        LocalDate bornCinema = LocalDate.of(1895, 12, 28);
        if (film.getId() == 0) film.setId(1);
        if (film.getName().equals("")) throw new ValidationException();
        if (film.getDescription().length() > 200) throw new ValidationException();
        if (film.getReleaseDate().isBefore(bornCinema)) throw new ValidationException();
        if (film.getDuration() < 0) throw new ValidationException();

    }

    @PutMapping
    public Film update(@RequestBody Film film) throws ValidationException {
        //if (films.size() == 0)  throw new ValidationException(); //когда список пуст
        for (int i = 0; i < films.size(); i++) {
            int id = films.get(i).getId();
            int idNew = film.getId();
            if (id == idNew) {
                checkFilm(film);
                films.remove(i);
                films.add(film);
                log.info("Получен запрос на редактирование фильма");
                return film;
            }
        }
        throw new ValidationException();
    }
}
