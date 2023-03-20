package ru.yandex.practicum.filmorate.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import ru.yandex.practicum.filmorate.exception.UserNotFoundException;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.InMemoryUserStorage;

import java.util.*;
import java.util.stream.Collectors;

@Slf4j
@Service
public class UserService {

    private InMemoryUserStorage inMemoryUserStorage;

    @Autowired
    public UserService(InMemoryUserStorage inMemoryUserStorage) {
        this.inMemoryUserStorage = inMemoryUserStorage;
    }

    public List<User> findAll() {
        return inMemoryUserStorage.findAll();
    }

    public User findUserById(int id) {
        return inMemoryUserStorage.findUserById(id);
    }

    public Set<Long> findUsersFriends(int id) {
        User user = inMemoryUserStorage.findUserById(id);
        if (user == null) throw new UserNotFoundException("Пользователь не найден");
        return user.getIdFriends();
    }

    public Set<Long> findUsersCommonFriends(@PathVariable int id, @PathVariable int friendId) {
        User user = inMemoryUserStorage.findUserById(id); //находим пользователей
        User user1 = inMemoryUserStorage.findUserById(friendId);
        if (user == null) throw new UserNotFoundException("Первый пользователь не найден");
        if (user1 == null) throw new UserNotFoundException("Второй пользователь не найден");
        Set<Long> idFriends = user.getIdFriends();    //извлекаем из них списки друзей
        Set<Long> idFriends1 = user1.getIdFriends();
        //находим общие элементы. общие друзья в обоих списках друзей
        Set<Long> common = idFriends.stream().filter(idFriends1::contains).collect(Collectors.toSet());
        return common;
    }

    public User create(User user) {
        log.info("Получен запрос на создание нового пользователя");
        return inMemoryUserStorage.create(user);
    }

    public User update(User user) {
        log.info("Получен запрос на редактирование пользователя");
        return inMemoryUserStorage.update(user);
    }

    public List<User> addFriend(int id, int friendId) {
        User user = inMemoryUserStorage.findUserById(id); //находим пользователей
        User user1 = inMemoryUserStorage.findUserById(friendId);
        if (user == null) throw new UserNotFoundException("Первый пользователь не найден");
        if (user1 == null) throw new UserNotFoundException("Второй пользователь не найден");
        Set<Long> idFriends = user.getIdFriends();    //извлекаем из них списки друзей
        Set<Long> idFriends1 = user1.getIdFriends();
        idFriends.add((long) id);                     //добавляем в их списки айди новых друзей
        idFriends1.add((long) friendId);
        user.setIdFriends(idFriends);                 //обновляем списки друзей пользователей
        user1.setIdFriends(idFriends1);
        List<User> friends = new ArrayList<>();       //делаем список пользователей с обновленными друзьями
        friends.add(inMemoryUserStorage.update(user)); //обновляем юзеров в хранилище
        friends.add(inMemoryUserStorage.update(user1));
        return friends;
    }

    public User deleteUser(int id) {
        return inMemoryUserStorage.deleteUser(id);
    }

    public User deleteUserFriend(int id, int friendId) {
        User user = inMemoryUserStorage.findUserById(id); //находим пользователей
        if (user == null) throw new UserNotFoundException("Первый пользователь не найден");
        Set<Long> idFriends = user.getIdFriends();    //извлекаем из них списки друзей
        idFriends.remove(friendId); //убираем друга из списка друзей
        user.setIdFriends(idFriends);                 //обновляем списки друзей пользователей
        return inMemoryUserStorage.update(user);
    }
}

