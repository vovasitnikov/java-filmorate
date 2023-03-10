package ru.yandex.practicum.filmorate.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Film {
    int id;
    String name;
    LocalDate releaseDate;
    String description;
    int duration;
}
