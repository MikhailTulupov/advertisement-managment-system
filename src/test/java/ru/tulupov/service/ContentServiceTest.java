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
import ru.tulupov.model.Page;
import ru.tulupov.repository.ContentRepository;

import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * Class {@link ContentServiceTest} testing {@link ContentService} methods.
 */
@ExtendWith(SpringExtension.class)
@Transactional
@SpringBootTest(classes = Application.class)
public class ContentServiceTest {
    @Autowired
    private ContentService contentService;

    @Autowired
    ContentRepository contentRepository;

    @AfterEach
    void destroyAll() {
        contentRepository.deleteAll();
    }

    @Test
    public void saveContentTest() {
        Content content = Content.builder()
                .id(UUID.randomUUID())
                .pages(
                Set.of(Page.builder().name("MAIN_PAGE").build(),
                        Page.builder().name("SHOP_PAGE").build()
                )).build();

        contentService.save(content);

        Assertions.assertEquals(1, contentRepository.count());
    }

    @Test
    public void saveContentListTest() {
        List<Content> contents = List.of(Content.builder()
                .id(UUID.randomUUID())
                .pages(
                        Set.of(Page.builder().name("MAIN_PAGE").build(),
                                Page.builder().name("SHOP_PAGE").build()
                        )).build(),
                Content.builder()
                        .id(UUID.randomUUID())
                        .pages(
                                Set.of(Page.builder().name("MAIN_PAGE").build(),
                                        Page.builder().name("SHOP_PAGE").build()
                                )).build());

        contentService.saveAll(contents);

        Assertions.assertEquals(2, contentRepository.count());
    }

    @Test
    public void getContentByIdTest() {
        Content content = Content.builder()
                .id(UUID.randomUUID())
                .pages(
                        Set.of(Page.builder().name("MAIN_PAGE").build(),
                                Page.builder().name("SHOP_PAGE").build()
                        )).build();
        contentService.save(content);

        Assertions.assertEquals(content.getId(), contentService.getById(content.getId()).getId());
    }

    @Test
    public void getAllContentTest() {
        List<Content> contents = List.of(Content.builder()
                        .id(UUID.randomUUID())
                        .pages(
                                Set.of(Page.builder().name("MAIN_PAGE").build(),
                                        Page.builder().name("SHOP_PAGE").build()
                                )).build(),
                Content.builder()
                        .id(UUID.randomUUID())
                        .pages(
                                Set.of(Page.builder().name("MAIN_PAGE").build(),
                                        Page.builder().name("SHOP_PAGE").build()
                                )).build());

        contentService.saveAll(contents);

        Assertions.assertEquals(2, contentService.getAll().size());
    }
}
