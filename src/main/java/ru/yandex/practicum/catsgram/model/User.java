package ru.yandex.practicum.catsgram.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

import java.time.Instant;

@Data
@EqualsAndHashCode(of = { "id" })
public class User {
    Long id;
    String email;
    String username;
    String password;
    Instant registrationDate;
}
