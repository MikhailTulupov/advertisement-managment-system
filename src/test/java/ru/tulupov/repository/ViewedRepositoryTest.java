package ru.tulupov.repository;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import ru.tulupov.ApplicationTest;
import ru.tulupov.model.Content;
import ru.tulupov.model.User;
import ru.tulupov.model.Viewed;

import java.util.*;

/**
 * Class {@link ViewedRepositoryTest} testing {@link ViewedRepositoryTest} CRUD methods.
 */
@ExtendWith(SpringExtension.class)
@Transactional
@SpringBootTest(classes = ApplicationTest.class)
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
        contentRepository.deleteAll();
        userRepository.deleteAll();
        viewedRepository.deleteAll();
    }

    @Test
    void findViewedById() {
        initFindViewedById();
        Optional<Viewed> byId = viewedRepository.findById(viewed.getId());
        byId.ifPresent(value -> Assertions.assertEquals(viewed.getId(), value.getId()));
    }

    private void initFindViewedById() {
        user = new User();
        Content content = new Content();
        userRepository.save(user);
        contentRepository.save(content);
        viewed = Viewed.builder().userGuid(user.getId()).contentGuid(content.getId()).build();
        viewedRepository.save(viewed);
    }

    @Test
    void findAllViewedContentByUserId() {
        initFindAllViewedContentByUserId();
        List<Content> contentList = viewedRepository.findAllByUserGuid(user.getId());
        Assertions.assertEquals(10, contentList.size());
    }

    @Test
    void findAllUsersByContentId() {
        initFindAllUsersByContentId();
        List<User> userList = viewedRepository.findAllByContentGuid(content.getId());
        Assertions.assertEquals(users, userList);
    }

    private void initFindAllViewedContentByUserId() {
        user = new User();
        userRepository.save(user);
        for (int i = 0; i < 10; i++) {
            Content content = new Content();
            contentRepository.save(content);
            Viewed viewed = Viewed.builder().userGuid(user.getId()).contentGuid(content.getId()).build();
            viewedRepository.save(viewed);
        }
    }

    private void initFindAllUsersByContentId() {
        users = new ArrayList<>();
        content = new Content();
        contentRepository.save(content);
        for (int i = 0; i < 10; i++) {
            User user = new User();
            userRepository.save(user);
            users.add(user);
            Viewed viewed = Viewed.builder().userGuid(user.getId()).contentGuid(content.getId()).build();
            viewedRepository.save(viewed);
        }
    }
}
