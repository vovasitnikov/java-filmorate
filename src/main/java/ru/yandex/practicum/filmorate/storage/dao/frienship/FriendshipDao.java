package ru.yandex.practicum.filmorate.storage.dao.frienship;

import java.util.Collection;

public interface FriendshipDao {

    void add(long fromUserID, long toUserID, boolean isMutual);

    void delete(long fromUserID, long toUserID);

    Collection<Long> getFromUserIDs(long toUserId);

    boolean contains(long fromUserID, long toUserID);
}

