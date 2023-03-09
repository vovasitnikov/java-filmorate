package ru.yandex.practicum.filmorate.model;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class Film {
    int id;
    String name;
    LocalDate releaseDate;
    String description;
    int duration;

    public Film(int id, String name, LocalDate releaseDate, String description, int duration) {
        this.id = id;
        this.name = name;
        this.releaseDate = releaseDate;
        this.description = description;
        this.duration = duration;
    }
}
