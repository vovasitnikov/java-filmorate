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
    int rate;
}
