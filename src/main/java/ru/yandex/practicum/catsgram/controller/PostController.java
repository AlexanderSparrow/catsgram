package ru.yandex.practicum.catsgram.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.catsgram.exception.NotFoundException;
import ru.yandex.practicum.catsgram.exception.ParameterNotValidException;
import ru.yandex.practicum.catsgram.model.Post;
import ru.yandex.practicum.catsgram.service.PostService;
import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping("/posts")
@AllArgsConstructor
public class PostController {
    private final PostService postService;

    @GetMapping
    public Collection<Post> findAll(
            @RequestParam(name = "requestFrom", defaultValue = "1", required = false) Optional<Integer> requestFrom,
            @RequestParam(name = "requestSize", defaultValue = "10", required = false) Optional<Integer> requestSize,
            @RequestParam(name = "sort", defaultValue = "desc", required = false) String sort) {

        if (requestSize.isEmpty()) {
            throw new ParameterNotValidException("size", "Не указан размер выборки.");
        }
        if (requestFrom.isEmpty()) {
            throw new ParameterNotValidException("from", "Не указан начальная страница.");
        }
        int from = requestFrom.get();
        int size = requestSize.get();
        if (size <= 0) {
            throw new ParameterNotValidException("size", "Некорректный размер выборки. Размер должен быть больше нуля");
        }
        if (from <= 0) {
            throw new ParameterNotValidException("from",
                    "Некорректный номер начальной страницы. Номер должен быть больше нуля");
        }
        return postService.findAll(from, size, sort);
    }

    @GetMapping("/{postId}")
    public Post findById(@PathVariable long postId) {
        return postService.findById(postId)
                .orElseThrow(() -> new NotFoundException("Пост с id = " + postId + " не найден"));
    }

    @PutMapping
    public Post update(@RequestBody Post newPost) {
        return postService.update(newPost);
    }
}