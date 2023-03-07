package ru.yandex.practicum.filmorate.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {
    public int id;

    private List<User> users = new ArrayList<>();

    @GetMapping
    public List<User> findAll() {
        return users;
    }

    @PostMapping
    public User create(@RequestBody User user) throws ValidationException {
        checkUser(user);
        id= id + 1;
        user.setId(id);
        users.add(user);
        log.info("Получен запрос на создание нового пользователя");
        return user;
    }

    private void checkUser(User user) throws ValidationException {
        if (user.getId() < 0) throw new ValidationException();
        if (user.getEmail().equals("")) throw new ValidationException();
        if (!user.getEmail().contains("@")) throw new ValidationException();
        if (user.getLogin().equals("")) throw new ValidationException();
        if (user.getLogin().contains(" ")) throw new ValidationException();
        if (user.getName() == null) {
            user.setName(user.getLogin());
        }
        if (user.getBirthday().isAfter(LocalDate.now())) {
            throw new ValidationException();
        }
    }

    @PutMapping
    public User update(@RequestBody User user) throws ValidationException {
        if (users.size() == 0)  throw new ValidationException(); //когда список пуст
        for (int i = 0; i < users.size(); i++) {
            int id = users.get(i).getId();
            int idNew = user.getId();
            if (id == idNew) {
                checkUser(user);
                users.remove(i);
                users.add(user);
                log.info("Получен запрос на редактирование пользователя");
                return user;
            }
        }
        throw new ValidationException();
    }
}
