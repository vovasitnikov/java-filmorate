package ru.yandex.practicum.filmorate.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.yandex.practicum.filmorate.model.User;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class UserControllerTest {
    private User user;
    private Map<Integer, User> mapListUser;

    @BeforeEach
    void load() {
        LocalDate birthday = LocalDate.of(1985, 9, 12);
        user = new User(1, "vova_sitnikov@mail.ru", "Vova", "Vova", birthday);
        mapListUser = new HashMap<>();
    }

    @Test
    void findAll() {
        mapListUser.put(user.getId(), user);
        assertEquals(1, mapListUser.size());
    }

    @Test
    void create() {
        mapListUser.put(user.getId(), user);
        User savedUser = mapListUser.get(1);
        assertEquals(user, savedUser);
    }

    @Test
    void update() {
        mapListUser.put(user.getId(), user);
        User savedUser = mapListUser.get(1);
        savedUser.setName("Sergey");
        mapListUser.put(savedUser.getId(), savedUser);
        assertEquals(savedUser, mapListUser.get(1));
    }
}