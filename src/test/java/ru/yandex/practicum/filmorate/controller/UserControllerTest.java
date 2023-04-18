package ru.yandex.practicum.filmorate.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.filmorate.exception.ValidationException;
import ru.yandex.practicum.filmorate.model.User;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class UserControllerTest {
    private User user;
    private Map<Integer, User> mapListUser;
    private Set<Long> friends = new HashSet<>();

/*    @BeforeEach
    void load() {
        LocalDate birthday = LocalDate.of(1985, 9, 12);
        user = new User(1, "vova_sitnikov@mail.ru", "Vova", "Vova", birthday, friends);
        mapListUser = new HashMap<>();
    }*/

/*    @Test
    void findAll() {
        mapListUser.put(user.getId(), user);
        assertEquals(1, mapListUser.size());
    }*/

/*    @Test
    void create() {
        mapListUser.put(user.getId(), user);
        User savedUser = mapListUser.get(1);
        assertEquals(user, savedUser);
    }*/

/*    @Test
    void createSubzeroId() {
        try {
            user.setId(-1);
            checkUser(user);
        } catch (Exception e) {
            assertEquals("Id пользователя отрицательный",
                    e.getMessage());
            return;
        }
        fail("Тест провален");
    }*/

    @Test
    void createEmptyEmail() {
        try {
            user.setEmail("");
            checkUser(user);
        } catch (Exception e) {
            assertEquals("Почта пустая",
                    e.getMessage());
            return;
        }
        fail("Тест провален");
    }

    @Test
    void createFailEmail() {
        try {
            user.setEmail("vova");
            checkUser(user);
        } catch (Exception e) {
            assertEquals("Почта не верная",
                    e.getMessage());
            return;
        }
        fail("Тест провален");
    }

    @Test
    void createEmptyLogin() {
        try {
            user.setLogin("");
            checkUser(user);
        } catch (Exception e) {
            assertEquals("Логин пустой",
                    e.getMessage());
            return;
        }
        fail("Тест провален");
    }

    @Test
    void createFailDate() {
        try {
            user.setBirthday(LocalDate.of(2985, 9, 12));
            checkUser(user);
        } catch (Exception e) {
            assertEquals("Дата рождения не верная",
                    e.getMessage());
            return;
        }
        fail("Тест провален");
    }

    private void checkUser(User user) {
        if (user.getId() < 0) throw new ValidationException("Id пользователя отрицательный");
        if (user.getEmail().isBlank()) throw new ValidationException("Почта пустая");
        if (!user.getEmail().contains("@")) throw new ValidationException("Почта не верная");
        if (user.getLogin().isBlank()) throw new ValidationException("Логин пустой");
        if (user.getName() == null) {
            user.setName(user.getLogin());
        }
        if (user.getBirthday().isAfter(LocalDate.now())) {
            throw new ValidationException("Дата рождения не верная");
        }
    }

/*    @Test
    void update() {
        mapListUser.put(user.getId(), user);
        User savedUser = mapListUser.get(1);
        savedUser.setName("Sergey");
        mapListUser.put(savedUser.getId(), savedUser);
        assertEquals(savedUser, mapListUser.get(1));
    }*/
}