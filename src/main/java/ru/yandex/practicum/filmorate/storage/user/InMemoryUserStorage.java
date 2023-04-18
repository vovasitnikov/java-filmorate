/*
package ru.yandex.practicum.filmorate.storage.user;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import ru.yandex.practicum.filmorate.exception.BadRequest;
import ru.yandex.practicum.filmorate.exception.UserNotFoundException;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Data
@Component
@Slf4j
public class InMemoryUserStorage implements UserStorage {

    public int id;
    private HashMap<Integer, User> users = new HashMap<>();

    public List<User> findAll() {
        return new ArrayList<>(users.values());
    }

    public User create(User user) {
        checkUser(user);
        user.setId(++id);
        users.put(id, user);
        log.info("Создан новый пользователь");
        return user;
    }

    private void checkUser(User user) {
        if (user.getEmail() != null) { //почта может быть null
            if (user.getEmail().isBlank()) throw new BadRequest("Почта пустая");
            if (!user.getEmail().contains("@")) throw new BadRequest("Почта не верная");
        }
        if (!(user.getLogin() == null)) {
            if (user.getLogin().isBlank()) throw new BadRequest("Логин пустой");
        }

        if (user.getName() == null || user.getName().isBlank()) {
            user.setName(user.getLogin());
        }
        if (user.getBirthday() == null)  throw new BadRequest("Дата пустая");
        if (user.getBirthday().isAfter(LocalDate.now())) {
            throw new BadRequest("Дата рождения не верная");
        }
    }

    public User findUserById(int id) {
        if (users.containsKey(id)) {
            return users.get(id);
        }
         throw new UserNotFoundException("Пользователя не существует");
    }

    public User update(User user) {
        int idNew = user.getId();
        if (users.containsKey(idNew)) {
            int id = users.get(idNew).getId();
            checkUser(user);
            users.put(id, user);
            log.info("Пользователь отредактирован");
            return user;
        }
        throw new ValidationException("Пользователя нет в базе");
    }

    public User deleteUser(int id) {
        if (users.containsKey(id)) {
            return users.remove(id);
        }
        throw new UserNotFoundException("Пользователя не существует");
    }


}
*/
