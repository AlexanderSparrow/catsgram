package ru.yandex.practicum.catsgram.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.Instant;

@Data
@EqualsAndHashCode(of = { "id" })
public class Post {
    Long id;
    long authorId;
    String description;
    Instant postDate;

    // Конструктор с аннотацией @JsonCreator для корректной десериализации
    @JsonCreator
    public Post(@JsonProperty("authorId") long authorId,
                @JsonProperty("description") String description) {
        this.authorId = authorId;
        this.description = description;
        this.postDate = Instant.now(); // Можно установить текущее время, если необходимо
    }

    // Конструктор по умолчанию (необходим для сериализации/десериализации)
    public Post() {
    }
}
