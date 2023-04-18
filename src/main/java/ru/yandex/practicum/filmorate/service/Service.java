package ru.yandex.practicum.filmorate.service;

import java.util.Collection;

public interface Service<T>  {

    T add(T element);

    T update(T element);

    T get(long elementID);

    Collection<T> getAll();

    default boolean contains(long elementID) {
        throw new UnsupportedOperationException("Метод не предназначен для использования на уровне сервиса");
    }
}
