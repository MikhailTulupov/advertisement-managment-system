package ru.tulupov.service;

import ru.tulupov.model.User;

import java.util.List;

public interface UserService {
    List<User> findAll();
}
