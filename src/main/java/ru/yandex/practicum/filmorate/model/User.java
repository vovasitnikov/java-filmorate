package ru.yandex.practicum.filmorate.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level= AccessLevel.PRIVATE)
public class User {

    @EqualsAndHashCode.Exclude
    @JsonIgnore
    final Set<Integer> friends = new HashSet<>();

    @EqualsAndHashCode.Exclude
    Integer id;

    @NotBlank(message = "Email must be not empty and not null")
    @Email(message = "Email must be format as email address: example@google.com")
    String email;

    @NotBlank(message = "Login must be not empty and not null")
    String login;

    String name;

    @PastOrPresent(message = "Birthday must be in the past, not future")
    LocalDate birthday;

    public void addFriend(Integer id) {
        friends.add(id);
    }

    public void deleteFriend(Integer id) {
        friends.remove(id);
    }

}
