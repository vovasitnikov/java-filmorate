package ru.yandex.practicum.filmorate.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.storage.InMemoryFilmStorage;

import java.util.*;

@Slf4j
@Service
public class FilmService {

    private InMemoryFilmStorage inMemoryFilmStorage;

    @Autowired
    public FilmService(InMemoryFilmStorage inMemoryFilmStorage) {
        this.inMemoryFilmStorage = inMemoryFilmStorage;
    }

    public List<Film> findAll() {
        return inMemoryFilmStorage.findAll();
    }

    public Film findFilmById(int id) {
        return inMemoryFilmStorage.findFilmById(id);
    }

    public Film create(Film film) throws ValidationException {
        log.info("Получен запрос на создание нового фильма");
        log.info("Фильм добавлен {}", film);
        return inMemoryFilmStorage.create(film);
    }

    public Film likeToFilm(int id, int IdUser) {
        Film film = inMemoryFilmStorage.findFilmById(id); //извлекаем фильм
        Set<Long> filmLikes = film.getLikes(); //извлекаем список лайков фильма
        filmLikes.add((long) IdUser); //добавляем в список айдишник пользователя, поставившего лайк
        film.setLikes(filmLikes); //обновляем список лайков в фильме
        return inMemoryFilmStorage.update(film); //обновляем хранилище
    }

    public Film deleteUserLike(int id, int userId) {
        Film film = inMemoryFilmStorage.findFilmById(id); //извлекаем фильм
        Set<Long> filmLikes = film.getLikes(); //извлекаем список лайков фильма
        filmLikes.remove(userId); //добавляем в список айдишник пользователя, поставившего лайк
        film.setLikes(filmLikes); //обновляем список лайков в фильме
        return inMemoryFilmStorage.update(film); //обновляем хранилище
    }

    public Set<Film> findPopularFilms(int count) {
        Set<Film> listOfFilms = new HashSet<>();
        HashMap<Integer, Film> films = inMemoryFilmStorage.getFilms();

        if (count == 0) {
            List<Integer> keys = new ArrayList<Integer>(films.keySet());
            for(int i = 0; i < 10; i++) {
                Integer id = keys.get(i);
                Film film = films.get(id);
                listOfFilms.add(film);
            }
            return listOfFilms;
        } else {
            TreeMap<Film, Integer> sortedFilms = new TreeMap<>(Comparator.comparingInt(o -> o.getLikes().size()));
            for (Map.Entry<Integer, Film> pair : films.entrySet()) {
                sortedFilms.put(pair.getValue(), pair.getKey());
            }
            for (Map.Entry<Film, Integer> pair : sortedFilms.entrySet()) {
                if (listOfFilms.size() == count) break;
                listOfFilms.add(pair.getKey());
            }
            return listOfFilms;
        }
    }


    public Film update(Film film) {
        log.info("Получен запрос на редактирование фильма");
        log.info("Фильм отредактирован и добавлен {}", film);
        return inMemoryFilmStorage.update(film);
    }
}