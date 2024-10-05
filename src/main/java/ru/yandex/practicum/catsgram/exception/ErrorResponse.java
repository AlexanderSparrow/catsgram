package ru.yandex.practicum.catsgram.exception;

import lombok.Getter;

@Getter
public record ErrorResponse(String error) {
}