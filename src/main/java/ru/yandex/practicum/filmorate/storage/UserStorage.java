package ru.yandex.practicum.filmorate.storage;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ru.yandex.practicum.filmorate.model.User;

import java.util.HashMap;
import java.util.List;

public interface UserStorage {

    int id = 0;

    HashMap<Integer, User> users = new HashMap<>();

    @GetMapping
    public List<User> findAll();

    @PostMapping
    public User create(@RequestBody User user);

    @PutMapping
    public User update(@RequestBody User user);
}
