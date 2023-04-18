package ru.yandex.practicum.filmorate.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Friendship {
    @NonNull
    private Long fromUserId;

    @NonNull
    private Long toUserId;

    @NonNull
    private Boolean isMutual;
}
