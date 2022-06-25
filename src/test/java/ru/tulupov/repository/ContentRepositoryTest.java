package ru.tulupov.repository;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import ru.tulupov.ApplicationTest;
import ru.tulupov.model.Content;
import ru.tulupov.model.Page;

import java.util.List;
import java.util.Optional;
import java.util.Set;

/**
 * Class {@link ContentRepositoryTest} testing {@link ContentRepository}  CRUD method.
 */
@ExtendWith(SpringExtension.class)
@Transactional
@SpringBootTest(classes = ApplicationTest.class)
public class ContentRepositoryTest {
    @Autowired
    private ContentRepository contentRepository;

    private Content content;
    private List<Content> contents;

    @BeforeEach
    void init() {
        content = new Content();
        contentRepository.save(content);
    }

    @AfterEach
    void destroyAll() {
        contentRepository.deleteAll();
    }

    @Test
    void findContentById() {
        Optional<Content> byId = contentRepository.findById(content.getId());
        byId.ifPresent(value -> Assertions.assertEquals(content.getId(), value.getId()));
    }

    @Test
    public void getSetPagesByContentId() {
        prepareData();
        Optional<Content> byId = contentRepository.findById(contents.get(0).getId());
        byId.ifPresent(value -> Assertions.assertEquals(2, value.getPages().size()));
    }

    private void prepareData() {
        Set<Page> pages = Set.of(
                Page.builder().name("MAIN_PAGE").build(),
                Page.builder().name("SHOP_PAGE").build()
        );
        contents = List.of(Content.builder().pages(pages).build(), Content.builder().pages(pages).build());
        contentRepository.saveAll(contents);
    }
}
