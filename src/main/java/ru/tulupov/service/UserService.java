package ru.tulupov.service;

import ru.tulupov.model.User;

import java.util.List;

public interface UserService {
    /**
     * Method return {@link List}<{@link User}>
     *
     * @return list of contents
     */
    List<User> getAll();

    /**
     * Method save {@link User} in database.
     *
     * @param user viewed
     */
    User save(User user);
}