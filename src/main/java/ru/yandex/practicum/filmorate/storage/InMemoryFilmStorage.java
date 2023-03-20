package ru.yandex.practicum.filmorate.storage;

import lombok.Data;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Data
@Component
public class InMemoryFilmStorage implements FilmStorage{
    public int idFilm;
    private HashMap<Integer, Film> films = new HashMap<>();

    public List<Film> findAll() {
        return new ArrayList<>(films.values());
    }

    public Film create(Film film) throws ValidationException {
        checkFilm(film);
        film.setId(++idFilm);
        films.put(idFilm, film);
        return film;
    }

    private void checkFilm(Film film) {
        LocalDate bornCinema = LocalDate.of(1895, 12, 28);
        if (film.getName().isBlank()) throw new ValidationException("Нет названия фильма");
        if (film.getName() == null) throw new ValidationException("Название фильма null");
        if (film.getDescription().length() > 200) throw new ValidationException("Описание слишком длинное");
        if (film.getDescription() == null) throw new ValidationException("Описание фильма null");
        if (film.getReleaseDate() == null)  throw new ValidationException("Дата пустая");
        if (film.getReleaseDate().isBefore(bornCinema)) throw new ValidationException("Дата выпуска слишком ранняя");
        if (film.getDuration() <= 0) throw new ValidationException("Продолжительность меньше нуля");
    }

    public Film findFilmById(int id) {
        if (films.containsKey(id)) {
            return films.get(id);
        }
        return null;
    }

    public Film update(Film film) {
        int idNew = film.getId();
        if(films.containsKey(idNew)) {
            int id = films.get(idNew).getId();
            checkFilm(film);
            films.put(id, film);
            return film;
        }
        throw new ValidationException("Такого фильма в базе нет");
    }
}

