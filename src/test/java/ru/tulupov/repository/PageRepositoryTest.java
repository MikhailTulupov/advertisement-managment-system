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
import ru.tulupov.model.Page;

import java.util.Optional;

/**
 * Class {@link PageRepositoryTest} testing {@link PageRepository} CRUD methods.
 */
@ExtendWith(SpringExtension.class)
@Transactional
@SpringBootTest(classes = ApplicationTest.class)
public class PageRepositoryTest {
    @Autowired
    private PageRepository pageRepository;
    private Page page;

    @BeforeEach
    void initUseCase() {
        page = Page.builder().name("MAIN_PAGE").build();
        pageRepository.save(page);
    }

    @AfterEach
    void destroyAll() {
        pageRepository.deleteAll();
    }

    @Test
    void findPageById() {
        Optional<Page> byId = pageRepository.findById(page.getId());
        byId.ifPresent(value -> Assertions.assertEquals(page.getId(), value.getId()));
    }


}
