package ru.yandex.practicum.filmorate.service.user;

import ru.yandex.practicum.filmorate.model.User;
import ru.yandex.practicum.filmorate.service.Service;

import java.util.Collection;

public interface UserService extends Service<User> {

    void addFriend(long userID, long friendID);

    void deleteFriend(long userID, long friendID);

    Collection<User> getFriends(long userID);

    Collection<User> getCommonFriends(long userID, long otherUserID);
}

