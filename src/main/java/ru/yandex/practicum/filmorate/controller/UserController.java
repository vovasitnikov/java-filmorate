package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {
    public int id;

    private HashMap<Integer, User> users = new HashMap<>();

    @GetMapping
    public List<User> findAll() {
        List<User> userList = new ArrayList<>();
        for (Map.Entry<Integer, User> user : users.entrySet()) {
            userList.add(user.getValue());
        }
        return userList;
    }

    @PostMapping
    public User create(@RequestBody User user) {
        checkUser(user);
        user.setId(++id);
        users.put(id, user);
        log.info("Получен запрос на создание нового пользователя");
        return user;
    }

    private void checkUser(User user) {
        if (user.getId() < 0) throw new ValidationException("Id пользователя отрицательный");
        if (user.getEmail().equals("")) throw new ValidationException("Почта пустая");
        if (!user.getEmail().contains("@")) throw new ValidationException("Почта не верная");
        if (user.getLogin().equals("")) throw new ValidationException("Логин пустой");
        if (user.getLogin().contains(" ")) throw new ValidationException("Логин содержит пробелы");
        if (user.getName() == null) {
            user.setName(user.getLogin());
        }
        if (user.getBirthday() == null)  throw new ValidationException("Дата пустая");
        if (user.getBirthday().isAfter(LocalDate.now())) {
            throw new ValidationException("Дата рождения не верная");
        }
    }

    @PutMapping
    public User update(@RequestBody User user) {
        int idNew = user.getId();
        if (users.containsKey(idNew)) {
            int id = users.get(idNew).getId();
            checkUser(user);
            users.put(id, user);
            log.info("Получен запрос на редактирование пользователя");
            return user;
        }
        throw new ValidationException("Пользователя нет в базе");
    }
}