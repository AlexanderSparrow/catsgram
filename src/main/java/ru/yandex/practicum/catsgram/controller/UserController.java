package ru.yandex.practicum.catsgram.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.yandex.practicum.catsgram.model.User;
import ru.yandex.practicum.catsgram.service.UserService;
import java.util.Collection;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

   /* public UserController(UserService userService) {
        this.userService = userService;
    }*/

    // Получение списка всех пользователей
    @GetMapping
    public Collection<User> findAll() {
        return userService.findAll();
    }

    // Добавление нового пользователя
    @PostMapping
    public User create(@RequestBody User user) {
        return userService.create(user);
    }

    // Обновление данных существующего пользователя
    @PutMapping
    public User update(@RequestBody User user) {
        return userService.update(user);
    }
}
