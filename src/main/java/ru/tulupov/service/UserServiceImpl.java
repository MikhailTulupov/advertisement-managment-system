package ru.tulupov.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.tulupov.model.User;
import ru.tulupov.repository.UserRepository;

import java.util.List;

/**
 * The {@link UserServiceImpl} class implements {@link UserService} interface methods.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository userRepository;

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }
}