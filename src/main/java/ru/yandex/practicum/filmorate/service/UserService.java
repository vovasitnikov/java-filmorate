package ru.yandex.practicum.filmorate.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import ru.yandex.practicum.filmorate.exception.UserNotFoundException;
import ru.yandex.practicum.filmorate.model.Film;
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

    public Set<User> findUsersFriends(int id) {
        User user = inMemoryUserStorage.findUserById(id);
        if (user == null) throw new UserNotFoundException("Пользователь не найден");
        //достанем нужных пользователей
        Set<User> friends = new HashSet<>();
        Set<Long> idFriends = user.getIdFriends(); //достаем список айдишников друзей
        for (Long idFriend : idFriends) {
            User friend = inMemoryUserStorage.findUserById(Math.toIntExact(idFriend));
            if (friend != null) {
                friends.add(friend);
            }
        }
        return friends;
    }

    public Set<Long> findUsersCommonFriends(int id, int friendId) {
        Set<Long> common = new HashSet<>();
        User user = inMemoryUserStorage.findUserById(id); //находим пользователей
        User user1 = inMemoryUserStorage.findUserById(friendId);
        if (user == null) throw new UserNotFoundException("Первый пользователь не найден");
        if (user1 == null) throw new UserNotFoundException("Второй пользователь не найден");
        Set<Long> idFriends = user.getIdFriends();    //извлекаем из них списки друзей
        Set<Long> idFriends1 = user1.getIdFriends();
        if (idFriends != null || idFriends1 != null) {
            //находим общие элементы. общие друзья в обоих списках друзей
            common = idFriends.stream().filter(idFriends1::contains).collect(Collectors.toSet());
        }
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

    public Set<User> addFriend(int id, int friendId) {
        Set<User> friends = new HashSet<>();     //делаем список пользователей с обновленными друзьями
        Set<Long> idFriends = new HashSet<>();
        Set<Long> idFriends1 = new HashSet<>();

        User user = inMemoryUserStorage.findUserById(id); //находим пользователей
        User user1 = inMemoryUserStorage.findUserById(friendId);
        if (user == null) throw new UserNotFoundException("Первый пользователь не найден");
        if (user1 == null) throw new UserNotFoundException("Второй пользователь не найден");
        if (user.getIdFriends() != null) idFriends = user.getIdFriends();    //извлекаем из них списки друзей
        if (user1.getIdFriends() != null) idFriends1 = user1.getIdFriends();
        idFriends.add((long) friendId);                     //добавляем в их списки айди новых друзей
        idFriends1.add((long) id);
        user.setIdFriends(idFriends);                 //обновляем списки друзей пользователей
        user1.setIdFriends(idFriends1);
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

