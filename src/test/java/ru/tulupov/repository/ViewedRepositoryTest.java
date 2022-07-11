package ru.tulupov.repository;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import ru.tulupov.Application;
import ru.tulupov.model.Content;
import ru.tulupov.model.User;
import ru.tulupov.model.Viewed;

import java.util.*;

/**
 * Class {@link ViewedRepositoryTest} testing {@link ViewedRepositoryTest} CRUD methods.
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = Application.class)
public class ViewedRepositoryTest {
    @Autowired
    private ViewedRepository viewedRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ContentRepository contentRepository;

    private User user;
    private Content content;
    private List<User> users;
    private Viewed viewed;

    @AfterEach
    void destroyAll() {
        viewedRepository.deleteAll();
        contentRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    void findViewedById() {
        initFindViewedById();
        Optional<Viewed> byId = viewedRepository.findById(viewed.getId());
        byId.ifPresent(value -> Assertions.assertEquals(viewed.getId(), value.getId()));
    }

    private void initFindViewedById() {
        User user = User.builder().id(UUID.randomUUID()).build();
        Content content = Content.builder().id(UUID.randomUUID()).build();
        viewed = Viewed.builder().user(user).content(content).build();
        userRepository.save(user);
        contentRepository.save(content);
        viewedRepository.save(viewed);
    }

    @Test
    void findAllViewedContentByUserId() {
        initFindAllViewedContentByUserId();
        List<Content> contentList = viewedRepository.findAllContentByUserId(user.getId());
        Assertions.assertEquals(10, contentList.size());
    }

    @Test
    void findAllUsersByContentId() {
        initFindAllUsersByContentId();
        List<User> userList = viewedRepository.findAllUsersByContentId(content.getId());
        Assertions.assertEquals(users, userList);
    }
    private void initFindAllViewedContentByUserId() {
        user = User.builder().id(UUID.randomUUID()).build();
        userRepository.save(user);
        for (int i = 0; i < 10; i++) {
            Content content = Content.builder().id(UUID.randomUUID()).build();
            Viewed viewed = Viewed.builder().user(user).content(content).build();
            contentRepository.save(content);
            viewedRepository.save(viewed);
        }
    }

    private void initFindAllUsersByContentId() {
        users = new ArrayList<>();
        content = Content.builder().id(UUID.randomUUID()).build();
        contentRepository.save(content);
        for (int i = 0; i < 10; i++) {
            User user = User.builder().id(UUID.randomUUID()).build();
            userRepository.save(user);
            users.add(user);
            Viewed viewed = Viewed.builder().user(user).content(content).build();
            viewedRepository.save(viewed);
        }
    }

    @Test
    public void setViewedTest() {
        initSetViewed();
        List<Viewed> all = viewedRepository.findAll();
        Assertions.assertEquals(1, all.size());
    }

    private void initSetViewed() {
        User user = User.builder().id(UUID.randomUUID()).build();
        Content content = Content.builder().id(UUID.randomUUID()).build();
        Viewed viewed1 = Viewed.builder().user(user).content(content).build();
        userRepository.save(user);
        contentRepository.save(content);
        viewedRepository.save(viewed1);
    }
}
