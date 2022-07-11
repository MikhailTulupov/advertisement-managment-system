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
import ru.tulupov.model.Page;
import ru.tulupov.repository.ContentRepository;
import ru.tulupov.repository.PageRepository;

import java.util.*;

/**
 * Class {@link ContentServiceTest} testing {@link ContentService} methods.
 */
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = Application.class)
public class ContentServiceTest {
    @Autowired
    private ContentService contentService;
    @Autowired
    ContentRepository contentRepository;
    @Autowired
    PageService pageService;
    @Autowired
    PageRepository pageRepository;

    @AfterEach
    void destroyAll() {
        pageRepository.deleteAll();
        contentRepository.deleteAll();
    }

    @Test
    public void saveContentTest() {
        Content content = Content.builder()
                .id(randomContentUUID())
                .pages(
                Set.of(Page.builder().name("MAIN_PAGE").build(),
                        Page.builder().name("SHOP_PAGE").build()
                )).build();

        Set<Page> pages = initPages(content);
        contentService.save(content);
        pageService.saveAll(List.copyOf(pages));

        Assertions.assertEquals(1, contentRepository.count());
    }

    private Set<Page> initPages(Content content) {
        Set<Page> pages = content.getPages();
        for (Page page : pages) {
            page.getContents().add(content);
        }
        return pages;
    }

    private UUID randomContentUUID() {
        return UUID.randomUUID();
    }

    @Test
    public void saveContentListTest() {
        List<Content> contents = List.of(Content.builder()
                .id(randomContentUUID())
                .pages(
                        Set.of(Page.builder().name("MAIN_PAGE").build(),
                                Page.builder().name("SHOP_PAGE").build()
                        )).build(),
                Content.builder()
                        .id(randomContentUUID())
                        .pages(
                                Set.of(Page.builder().name("MAIN_PAGE").build(),
                                        Page.builder().name("SHOP_PAGE").build()
                                )).build());


        List<Set<Page>> pages = getPageSet(contents);

        contentService.saveAll(contents);
        for (Set<Page> pageSet : pages) {
            pageService.saveAll(List.copyOf(pageSet));
        }

        Assertions.assertEquals(2, contentRepository.count());
    }

    private List<Set<Page>> getPageSet(List<Content> contents) {
        List<Set<Page>> pages = new ArrayList<>();

        for (Content content : contents) {
            Set<Page> pageSet = initPages(content);
            pages.add(pageSet);
        }
        return pages;
    }

    @Test
    public void getContentByIdTest() {
        Content content = Content.builder()
                .id(randomContentUUID())
                .pages(
                        Set.of(Page.builder().name("MAIN_PAGE").build(),
                                Page.builder().name("SHOP_PAGE").build()
                        )).build();

        Set<Page> pages = initPages(content);

        contentService.save(content);
        pageService.saveAll(List.copyOf(pages));

        Assertions.assertEquals(content.getId(), contentService.getById(content.getId()).getId());
    }

    @Test
    public void getAllContentTest() {
        List<Content> contents = List.of(Content.builder()
                        .id(randomContentUUID())
                        .pages(
                                Set.of(Page.builder().name("MAIN_PAGE").build(),
                                        Page.builder().name("SHOP_PAGE").build()
                                )).build(),
                Content.builder()
                        .id(randomContentUUID())
                        .pages(
                                Set.of(Page.builder().name("MAIN_PAGE").build(),
                                        Page.builder().name("SHOP_PAGE").build()
                                )).build());

        List<Set<Page>> pages = getPageSet(contents);

        contentService.saveAll(contents);
        for (Set<Page> pageSet : pages) {
            pageService.saveAll(List.copyOf(pageSet));
        }

        Assertions.assertEquals(2, contentService.getAll().size());
    }
}