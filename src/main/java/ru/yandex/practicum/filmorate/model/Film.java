package ru.yandex.practicum.filmorate.model;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Film {
    Long id;

    @NotNull(message = "У фильма должно быть имя")
    @NotBlank(message = "Имя не может быть пустым")
    String name;

    @NotNull(message = "У фильма должна быть указана дата релиза")
    LocalDate releaseDate;

    @NotNull(message = "У фильма должно быть описание")
    @Size(max = 200, message = "Описание не должно больше 200 символов")
    String description;

    @NotNull(message = "У фильма должна быть указана продолжительность")
    @Positive(message = "Продолжительность фильма не может быть отрицательной")
    int duration;

    @NotNull(message = "У фильма должен быть указан рейтинг MPA")
    private Mpa mpa;

    Set<Long> likes = new HashSet<>();
    private Set<Genre> genres = new HashSet<>();

}
