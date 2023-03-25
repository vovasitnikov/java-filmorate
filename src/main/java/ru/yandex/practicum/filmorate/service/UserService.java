package ru.yandex.practicum.filmorate.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.storage.InMemoryUserStorage;
import ru.yandex.practicum.filmorate.storage.UserStorage;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService {

    private UserStorage inMemoryUserStorage;

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

    public List<User> findUsersFriends(int id) {
        User user = inMemoryUserStorage.findUserById(id);
        //достанем нужных пользователей
        List<User> friends = new ArrayList<>();
        Set<Long> idFriends = user.getIdFriends(); //достаем список айдишников друзей
        for (Long idFriend : idFriends) {
            User friend = inMemoryUserStorage.findUserById(Math.toIntExact(idFriend));
            if (friend != null) {
                friends.add(friend);
            }
        }
        return friends;
    }

    public List<User> findUsersCommonFriends(int id, int friendId) {
        Set<Long> common = new HashSet<>();
        User user = inMemoryUserStorage.findUserById(id); //находим пользователей
        User userFriend = inMemoryUserStorage.findUserById(friendId);
        Set<Long> idFriends = user.getIdFriends();    //извлекаем из них списки друзей
        Set<Long> idFriends1 = userFriend.getIdFriends();
        if (idFriends != null && idFriends1 != null) {
            //находим общие элементы. общие друзья в обоих списках друзей
            common = idFriends.stream().filter(idFriends1::contains).collect(Collectors.toSet());
        }
        //теперь следует вернуть сами объекты - общие друзья
        List<User> commonFriends = new ArrayList<>();
        for (Long idFriend : common) {
            User friend = inMemoryUserStorage.findUserById(Math.toIntExact(idFriend));
            if (friend != null) {
                commonFriends.add(friend);
            }
        }
        return commonFriends;
    }

    public User create(User user) {
        return inMemoryUserStorage.create(user);
    }

    public User update(User user) {
        return inMemoryUserStorage.update(user);
    }

    public Set<User> addFriend(int id, int friendId) {
        Set<User> friends = new HashSet<>();     //делаем список пользователей с обновленными друзьями
        Set<Long> idFriends = new HashSet<>();
        Set<Long> idFriends1 = new HashSet<>();
        User user = inMemoryUserStorage.findUserById(id); //находим пользователей
        User userFriend = inMemoryUserStorage.findUserById(friendId);
        if (user.getIdFriends() != null) idFriends = user.getIdFriends();    //извлекаем из них списки друзей
        if (userFriend.getIdFriends() != null) idFriends1 = userFriend.getIdFriends();
        idFriends.add((long) friendId);                     //добавляем в их списки айди новых друзей
        idFriends1.add((long) id);
        user.setIdFriends(idFriends);                 //обновляем списки друзей пользователей
        userFriend.setIdFriends(idFriends1);
        friends.add(inMemoryUserStorage.update(user)); //обновляем юзеров в хранилище
        friends.add(inMemoryUserStorage.update(userFriend));
        return friends;
    }

    public User deleteUser(int id) {
        return inMemoryUserStorage.deleteUser(id);
    }

    public User deleteUserFriend(int id, int friendId) {
        User user = inMemoryUserStorage.findUserById(id); //находим пользователей
        User userFriend = inMemoryUserStorage.findUserById(friendId); //находим пользователей
        Set<Long> idFriends = user.getIdFriends();    //извлекаем из них списки друзей
        Set<Long> idFriendFriends = userFriend.getIdFriends();    //извлекаем из них списки друзей
        idFriends.remove(friendId); //убираем друга из списка друзей
        idFriendFriends.remove(id); //убираем друга из списка друзей
        user.setIdFriends(idFriends);                 //обновляем списки друзей пользователей
        userFriend.setIdFriends(idFriendFriends);                 //обновляем списки друзей пользователей
        return inMemoryUserStorage.update(user);
    }
}

