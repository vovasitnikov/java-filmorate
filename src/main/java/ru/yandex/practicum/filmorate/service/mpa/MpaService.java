package ru.yandex.practicum.filmorate.service.mpa;

import ru.yandex.practicum.filmorate.model.Mpa;
import ru.yandex.practicum.filmorate.service.Service;

public interface MpaService extends Service<Mpa> {

    default Mpa add(Mpa element) {
        throw new UnsupportedOperationException("Используйте data.sql чтобы добавить новый рейтинг MPA");
    }

    default Mpa update(Mpa element) {
        throw new UnsupportedOperationException("Используйте data.sql чтобы обновить рейтинг MPA");
    }
}
