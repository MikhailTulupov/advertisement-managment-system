package ru.tulupov.repository;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import ru.tulupov.Application;
import ru.tulupov.model.Content;
import ru.tulupov.model.Page;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

/**
 * Class {@link ContentRepositoryTest} testing {@link ContentRepository}  CRUD method.
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = Application.class)
public class ContentRepositoryTest {
    @Autowired
    ContentRepository contentRepository;
    @Autowired
    PageRepository pageRepository;

    @AfterEach
    void destroyAll() {
        pageRepository.deleteAll();
        contentRepository.deleteAll();
    }

    @Test
    void findContentById() {
        Content content = Content.builder().id(UUID.randomUUID()).build();
        contentRepository.save(content);
        Optional<Content> byId = contentRepository.findById(content.getId());
        byId.ifPresent(value -> Assertions.assertEquals(content.getId(), value.getId()));
    }

    @Test
    public void getSetPagesByContentId() {
        Set<Page> pages = Set.of(
                Page.builder().name("MAIN_PAGE").build(),
                Page.builder().name("SHOP_PAGE").build()
        );

        List<Content> contents = List.of(
                Content.builder().id(UUID.randomUUID()).pages(pages).build(),
                Content.builder().id(UUID.randomUUID()).pages(pages).build());
        for (Page page: pages) {
            page.setContents(Set.copyOf(contents));
        }

        contentRepository.saveAll(contents);
        pageRepository.saveAll(List.copyOf(pages));
        Optional<Content> byId = contentRepository.findById(contents.get(0).getId());
        byId.ifPresent(value -> Assertions.assertEquals(2, value.getPages().size()));
    }
}
