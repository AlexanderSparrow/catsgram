package ru.yandex.practicum.catsgram.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NonNull;

@Data
@EqualsAndHashCode(of = { "id" })
public class Image {
    Long id;
    long postId;
    @NonNull
    String originalFileName;
    @NonNull
    String filePath;
}
