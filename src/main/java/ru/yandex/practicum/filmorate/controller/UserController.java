package ru.yandex.practicum.filmorate.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.service.UserService;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> findAll() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Optional<User> findById(@PathVariable int id) {
        return Optional.ofNullable(userService.findUserById(id));
    }

    @GetMapping("/{id}/friends")                 //возвращаем список пользователей, являющихся его друзьями
    public List<User> findUsersFriends(@PathVariable int id) {
        return userService.findUsersFriends(id);
    }

    @GetMapping("/{id}/friends/common/{otherId}")  //список друзей, общих с другим пользователем
    public List<User> findUsersCommonFriends(@PathVariable int id, @PathVariable int otherId) {
      return userService.findUsersCommonFriends(id, otherId);
    }

    @PostMapping
    public User create(@RequestBody User user) {
        return userService.create(user);
    }

    @PutMapping
    public User update(@RequestBody User user) {
       return userService.update(user);
    }

    @PutMapping("/{id}/friends/{friendId}")     //добавление в друзья
    public Set<User> addFriend(@PathVariable int id, @PathVariable int friendId) {
        return userService.addFriend(id, friendId);
    }

    @DeleteMapping("/{id}")
    public User deleteUser(@PathVariable int id) {
        return userService.deleteUser(id);
    }

    @DeleteMapping("/{id}/friends/{friendId}")     //удаление из друзей
    public User deleteUserFriend(@PathVariable int id, @PathVariable int friendId) {
      return  deleteUserFriend(id, friendId);
    }
}
