package ru.yandex.practicum.filmorate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.exception.UserNotFoundException;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.Film;
import ru.yandex.practicum.filmorate.storage.FilmStorage;
import ru.yandex.practicum.filmorate.storage.InMemoryFilmStorage;
import ru.yandex.practicum.filmorate.storage.UserStorage;

import java.util.*;

@Service
public class FilmService {

    private FilmStorage inMemoryFilmStorage;
    private UserStorage inMemoryUserStorage;

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
        return inMemoryFilmStorage.create(film);
    }

    public Film likeToFilm(int id, int userId) {
        Film film = inMemoryFilmStorage.findFilmById(id); //извлекаем фильм
        Set<Long> filmLikes = new HashSet<>();
        //извлекаем список лайков фильма)
        if (film.getLikes() != null) {
            filmLikes = film.getLikes();
        }
        filmLikes.add((long) userId); //добавляем в список айдишник пользователя, поставившего лайк
        film.setLikes(filmLikes); //обновляем список лайков в фильме
        return inMemoryFilmStorage.update(film); //обновляем хранилище
    }

    public Film deleteUserLike(int id, int userId) {
        if (userId < 0) throw new UserNotFoundException("Пользователя не существует");
        //inMemoryUserStorage.findUserById(id);
        Film film = inMemoryFilmStorage.findFilmById(id); //извлекаем фильм
        Set<Long> filmLikes = film.getLikes(); //извлекаем список лайков фильма
        filmLikes.remove(userId); //добавляем в список айдишник пользователя, поставившего лайк
        film.setLikes(filmLikes); //обновляем список лайков в фильме
        return inMemoryFilmStorage.update(film); //обновляем хранилище
    }

    public List<Film> findPopularFilms(int count) {
        List<Film> listOfFilms = new ArrayList<>();
        HashMap<Integer, Film> films = inMemoryFilmStorage.getFilms();
            TreeMap<Integer, Film> sortedFilms = new TreeMap<>(Collections.reverseOrder());
            for (Map.Entry<Integer, Film> pair : films.entrySet()) {
                Film film = pair.getValue();
                Set<Long> likes = film.getLikes();
                int countLikes = 0;
                if (likes != null) {
                    countLikes = likes.size();
                }
                sortedFilms.put(countLikes, pair.getValue());
            }

            for (Map.Entry<Integer, Film> pair : sortedFilms.entrySet()) {
                if (listOfFilms.size() == count) break;
                listOfFilms.add(pair.getValue());
            }
            return listOfFilms;
    }

    public Film update(Film film) {
        return inMemoryFilmStorage.update(film);
    }
}