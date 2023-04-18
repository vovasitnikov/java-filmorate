package ru.yandex.practicum.filmorate.service.mpa;

import ru.yandex.practicum.filmorate.model.Mpa;

public interface MpaService  {

    default Mpa add(Mpa element) {
        throw new UnsupportedOperationException("Используйте data.sql чтобы добавить новый рейтинг MPA");
    }

    default Mpa update(Mpa element) {
        throw new UnsupportedOperationException("Используйте data.sql чтобы обновить рейтинг MPA");
    }
}
