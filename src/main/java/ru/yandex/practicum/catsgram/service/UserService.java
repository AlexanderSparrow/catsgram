package ru.yandex.practicum.catsgram.service;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import ru.yandex.practicum.catsgram.exception.ConditionsNotMetException;
import ru.yandex.practicum.catsgram.exception.DuplicatedDataException;
import ru.yandex.practicum.catsgram.model.User;

import java.time.Instant;
import java.util.*;

@Service
public class UserService {

    private final Map<Long, User> users = new HashMap<>();
    private final Map<String, User> usersByEmail = new HashMap<>(); // Для быстрого поиска по email

    public UserService() {
    }

    // Получение списка всех пользователей
    public Collection<User> findAll() {
        return users.values();
    }

    // Добавление нового пользователя
    public User create(@RequestBody User user) {
        // Проверяем, что указан email
        if (user.getEmail() == null || user.getEmail().isBlank()) {
            throw new ConditionsNotMetException("Имейл должен быть указан");
        }
        // Проверяем, что email уникален
        if (usersByEmail.containsKey(user.getEmail())) {
            throw new DuplicatedDataException("Этот имейл уже используется");
        }
        // Создаем нового пользователя
        user.setId(getNextId());
        user.setRegistrationDate(Instant.now()); // Устанавливаем дату регистрации
        users.put(user.getId(), user);
        usersByEmail.put(user.getEmail(), user);
        return user;
    }


    // Обновление данных существующего пользователя
    public User update(@RequestBody User user) {
        // Проверяем, что указан id
        if (user.getId() == null) {
            throw new ConditionsNotMetException("Id должен быть указан");
        }
        // Ищем пользователя в системе
        User existingUser = users.get(user.getId());
        if (existingUser == null) {
            throw new ConditionsNotMetException("Пользователь не найден");
        }
        // Проверяем, что новый email уникален, если он указан
        if (user.getEmail() != null && !user.getEmail().equals(existingUser.getEmail()) && usersByEmail.containsKey(user.getEmail())) {
            throw new DuplicatedDataException("Этот имейл уже используется");
        }
        // Обновляем только те поля, которые не равны null
        if (user.getEmail() != null) {
            usersByEmail.remove(existingUser.getEmail());
            existingUser.setEmail(user.getEmail());
            usersByEmail.put(user.getEmail(), existingUser);
        }
        if (user.getUsername() != null) {
            existingUser.setUsername(user.getUsername());
        }
        if (user.getPassword() != null) {
            existingUser.setPassword(user.getPassword());
        }
        return existingUser;
    }

    // Вспомогательный метод для генерации идентификатора нового пользователя
    private long getNextId() {
        long currentMaxId = users.keySet()
                .stream()
                .mapToLong(id -> id)
                .max()
                .orElse(0);
        return ++currentMaxId;
    }
    //@Autowired
    public Optional<User> findUserById(Long userId) {
        return Optional.ofNullable(users.get(userId));
    }
}
