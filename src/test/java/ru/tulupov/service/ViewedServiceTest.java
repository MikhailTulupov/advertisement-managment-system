package ru.tulupov.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.tulupov.Application;
import ru.tulupov.model.Content;
import ru.tulupov.model.User;
import ru.tulupov.model.Viewed;
import ru.tulupov.repository.ContentRepository;
import ru.tulupov.repository.UserRepository;
import ru.tulupov.repository.ViewedRepository;

import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * Class {@link ViewedServiceTest} testing {@link ViewedService} methods.
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = Application.class)
public class ViewedServiceTest {
    @Autowired
    ViewedService viewedService;
    @Autowired
    ContentService contentService;
    @Autowired
    UserService userService;
    @Autowired
    ViewedRepository viewedRepository;
    @Autowired
    ContentRepository contentRepository;
    @Autowired
    UserRepository userRepository;

    @AfterEach
    void destroyAll() {
        viewedRepository.deleteAll();
        contentRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    public void saveViewedTest() {
        Viewed viewed = Viewed
                .builder()
                .user(getRandomUser())
                .content(getRandomContent())
                .build();

        saveUser(viewed);
        saveContent(viewed);

        viewedService.save(viewed);

        Assertions.assertEquals(1, viewedRepository.count());
    }

    private void saveUser(Viewed viewed) {
        viewed.getUser().setViewedSet(Set.of(viewed));
        userService.save(viewed.getUser());
    }

    private Content getRandomContent() {
        return Content.builder().id(UUID.randomUUID()).build();
    }

    @Test
    public void saveViewedListTest() {
        List<Viewed> viewedList = List.of(
                Viewed
                        .builder()
                        .user(getRandomUser())
                        .content(getRandomContent())
                        .build(),
                Viewed
                        .builder()
                        .user(getRandomUser())
                        .content(getRandomContent())
                        .build()
        );

        for (Viewed viewed : viewedList) {
            saveContent(viewed);
            saveUser(viewed);
        }

        viewedService.saveAll(viewedList);

        Assertions.assertEquals(2, viewedService.getAll().size());
    }

    @Test
    public void getViewedById() {
        Viewed viewed = Viewed
                .builder()
                .user(getRandomUser())
                .content(getRandomContent())
                .build();
        saveUser(viewed);
        saveContent(viewed);
        viewedService.save(viewed);

        Assertions.assertEquals(viewed.getId(), viewedService.getById(viewed.getId()).getId());
    }

    private void saveContent(Viewed viewed) {
        viewed.getContent().setViewedSet(Set.of(viewed));
        contentService.save(viewed.getContent());
    }

    private User getRandomUser() {
        return User.builder().id(UUID.randomUUID()).build();
    }

    @Test
    public void getViewedListTest() {
        List<Viewed> viewedList = List.of(
                Viewed
                        .builder()
                        .user(getRandomUser())
                        .content(getRandomContent())
                        .build(),
                Viewed
                        .builder()
                        .user(getRandomUser())
                        .content(getRandomContent())
                        .build()
        );

        for (Viewed viewed : viewedList) {
            saveContent(viewed);
            saveUser(viewed);
        }

        viewedService.saveAll(viewedList);

        Assertions.assertEquals(viewedList, viewedService.getAll());
    }

    @Test
    public void findAllUsersByContentId() {
        Content content = getRandomContent();
        List<Viewed> viewedList = List.of(
                Viewed
                        .builder()
                        .user(getRandomUser())
                        .content(content)
                        .build(),
                Viewed
                        .builder()
                        .user(getRandomUser())
                        .content(content)
                        .build(),
                Viewed
                        .builder()
                        .user(getRandomUser())
                        .content(content)
                        .build()
        );

        for (Viewed viewed : viewedList) {
            saveContent(viewed);
            saveUser(viewed);
        }

        viewedService.saveAll(viewedList);

        Assertions.assertEquals(3, viewedService.getAllUsersByContentId(content.getId()).size());
    }

    @Test
    public void findAllContentByUserId() {
        User user = getRandomUser();
        List<Viewed> viewedList = List.of(
                Viewed
                        .builder()
                        .user(user)
                        .content(getRandomContent())
                        .build(),
                Viewed
                        .builder()
                        .user(user)
                        .content(getRandomContent())
                        .build(),
                Viewed
                        .builder()
                        .user(user)
                        .content(getRandomContent())
                        .build()
        );

        for (Viewed viewed : viewedList) {
            saveContent(viewed);
            saveUser(viewed);
        }

        viewedService.saveAll(viewedList);

        Assertions.assertEquals(3, viewedService.getAllContentByUserId(user.getId()).size());
    }
}
