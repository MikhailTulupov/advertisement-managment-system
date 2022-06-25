package ru.tulupov.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import ru.tulupov.ApplicationTest;
import ru.tulupov.model.User;

import java.util.Optional;

/**
 * Class {@link UserRepositoryTest} testing {@link UserRepository} CRUD methods.
 */
@ExtendWith(SpringExtension.class)
@Transactional
@SpringBootTest(classes = ApplicationTest.class)
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;
    private User user;

    @BeforeEach
    void initUseCase() {
        user = User.builder().build();
        userRepository.save(user);
    }

    @AfterEach
    void destroyAll() {
        userRepository.deleteAll();
    }

    @Test
    void findUserById() {
        Optional<User> byId = userRepository.findById(user.getId());
        byId.ifPresent(value -> Assertions.assertEquals(user.getId(), value.getId()));
    }
}
