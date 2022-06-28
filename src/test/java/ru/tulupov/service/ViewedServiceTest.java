package ru.tulupov.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import ru.tulupov.Application;
import ru.tulupov.model.Content;
import ru.tulupov.model.User;
import ru.tulupov.model.Viewed;
import ru.tulupov.repository.ViewedRepository;

import java.util.List;
import java.util.UUID;

/**
 * Class {@link ViewedServiceTest} testing {@link ViewedService} methods.
 */
@ExtendWith(SpringExtension.class)
@Transactional
@SpringBootTest(classes = Application.class)
public class ViewedServiceTest {
    @Autowired
    ViewedService viewedService;

    @Autowired
    ViewedRepository viewedRepository;

    @AfterEach
    void destroyAll() {
        viewedRepository.deleteAll();
    }

    @Test
    public void saveViewedTest() {
        Viewed viewed = Viewed
                .builder()
                .user(new User(UUID.randomUUID()))
                .content(new Content(UUID.randomUUID()))
                .build();

        viewedService.save(viewed);

        Assertions.assertEquals(1, viewedRepository.count());
    }

    @Test
    public void saveViewedListTest() {
        List<Viewed> viewedList = List.of(
                Viewed
                        .builder()
                        .user(new User(UUID.randomUUID()))
                        .content(new Content(UUID.randomUUID()))
                        .build(),
                Viewed
                        .builder()
                        .user(new User(UUID.randomUUID()))
                        .content(new Content(UUID.randomUUID()))
                        .build()
        );

        viewedService.saveAll(viewedList);

        Assertions.assertEquals(2, viewedService.getAll().size());
    }

    @Test
    public void getViewedById() {
        Viewed viewed = Viewed
                .builder()
                .user(new User(UUID.randomUUID()))
                .content(new Content(UUID.randomUUID()))
                .build();
        viewedService.save(viewed);

        Assertions.assertEquals(viewed.getId(), viewedService.getById(viewed.getId()).getId());
    }

    @Test
    public void getViewedListTest() {
        List<Viewed> viewedList = List.of(
                Viewed
                        .builder()
                        .user(new User(UUID.randomUUID()))
                        .content(new Content(UUID.randomUUID()))
                        .build(),
                Viewed
                        .builder()
                        .user(new User(UUID.randomUUID()))
                        .content(new Content(UUID.randomUUID()))
                        .build()
        );

        viewedService.saveAll(viewedList);

        Assertions.assertEquals(viewedList, viewedService.getAll());
    }

    @Test
    public void findAllUsersByContentId() {
        Content content = new Content(UUID.randomUUID());
        List<Viewed> viewedList = List.of(
                Viewed
                        .builder()
                        .user(new User(UUID.randomUUID()))
                        .content(content)
                        .build(),
                Viewed
                        .builder()
                        .user(new User(UUID.randomUUID()))
                        .content(content)
                        .build(),
                Viewed
                        .builder()
                        .user(new User(UUID.randomUUID()))
                        .content(content)
                        .build()
        );

        viewedService.saveAll(viewedList);

        Assertions.assertEquals(3, viewedService.findAllUsersByContentId(content.getId()).size());
    }

    @Test
    public void findAllContentByUserId() {
        User user = new User(UUID.randomUUID());
        List<Viewed> viewedList = List.of(
                Viewed
                        .builder()
                        .user(user)
                        .content(new Content(UUID.randomUUID()))
                        .build(),
                Viewed
                        .builder()
                        .user(user)
                        .content(new Content(UUID.randomUUID()))
                        .build(),
                Viewed
                        .builder()
                        .user(user)
                        .content(new Content(UUID.randomUUID()))
                        .build()
        );

        viewedService.saveAll(viewedList);

        Assertions.assertEquals(3, viewedService.findAllContentByUserId(user.getId()).size());
    }
}
