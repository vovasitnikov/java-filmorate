package ru.yandex.practicum.filmorate.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    Long id;
    @NotNull(message = "У пользователя должна быть указанна эл.почта")
    @Email(message = "Некорректная почта")
    String email;

    @NotNull(message = "У пользователя должен быть указан логин")
    @NotBlank(message = "Логин не может быть пустым")
    @Pattern(regexp = "\\S+", message = "В логине не могут находиться пробелы")
    String login;

    String name;

    @NotNull(message = "У пользователя должна быть указанна дата рождения")
    @Past(message = "Дата рождения не может быть в будущем")
    LocalDate birthday;

    Set<Long> idFriends = new HashSet<>();

}
