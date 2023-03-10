package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/films")
@Slf4j
public class FilmController {

    public int idFilm;
    private HashMap<Integer, Film> films = new HashMap<>();

    @GetMapping
    public List<Film> findAll() {
        List<Film> filmList = new ArrayList<>();
        for(Map.Entry<Integer, Film> user : films.entrySet()) {
            filmList.add(user.getValue());
        }
        return filmList;
    }

    @PostMapping
    public Film create(@RequestBody Film film) throws ValidationException {
        checkFilm(film);
        film.setId(++idFilm);
        films.put(idFilm, film);
        log.info("Получен запрос на создание нового фильма");
        log.info("Фильм добавлен {}", film);
        return film;
    }

    private void checkFilm(Film film) {
        LocalDate bornCinema = LocalDate.of(1895, 12, 28);
        if (film.getId() == 0) film.setId(1);
        if (film.getName().equals("")) throw new ValidationException("Нет названия фильма");
        if (film.getDescription().length() > 200) throw new ValidationException("Описание слишком длинное");
        if (film.getReleaseDate() == null)  throw new ValidationException("Дата пустая");
        if (film.getReleaseDate().isBefore(bornCinema)) throw new ValidationException("Дата выпуска слишком ранняя");
        if (film.getDuration() < 0) throw new ValidationException("Продолжительность меньше нуля");

    }

    @PutMapping
    public Film update(@RequestBody Film film) {
        int idNew = film.getId();
        if(films.containsKey(idNew)) {
            int id = films.get(idNew).getId();
            checkFilm(film);
            films.put(id, film);
            log.info("Получен запрос на редактирование фильма");
            log.info("Фильм отредактирован и добавлен {}", film);
            return film;
        }
        throw new ValidationException("Такого фильма в базе нет");
    }
}