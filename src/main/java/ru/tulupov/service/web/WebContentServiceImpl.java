package ru.tulupov.service.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.tulupov.mapper.ContentMapper;
import ru.tulupov.model.Content;

import ru.tulupov.model.Page;
import ru.tulupov.model.web.WebContent;
import ru.tulupov.service.ContentService;
import ru.tulupov.service.PageService;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import ru.tulupov.service.ContentServiceImpl;

import java.util.UUID;

/**
 * The {@link WebContentServiceImpl} class implements {@link WebContentService} methods.
 */
@Service
public class WebContentServiceImpl implements WebContentService {
    @Autowired
    ContentService contentService;
    @Autowired
    PageService pageService;

    @Override
    public void save(WebContent webContent) {
        Content content = ContentMapper.INSTANCE.webContentToContent(webContent);
        Set<Page> pages = content.getPages();
        pageService.saveAll(List.copyOf(pages));
        content.setPages(pages);
        contentService.save(content);
    }

    @Override
    public void saveAll(List<WebContent> webContentList) {
        List<Content> contents = new ArrayList<>();
        for (WebContent webContent: webContentList) {
            Content content = ContentMapper.INSTANCE.webContentToContent(webContent);
            contents.add(content);
        }
        contentService.saveAll(contents);
    }

    @Override
    public WebContent getById(String uuid) {
        Content content = contentService.getById(UUID.fromString(uuid));
        return ContentMapper.INSTANCE.contentToWebContent(content);
    }

    @Override
    public List<WebContent> getAll() {
        List<Content> contents = contentService.getAll();
        List<WebContent> webContents = new ArrayList<>();
        for (Content content: contents) {
            webContents.add(ContentMapper.INSTANCE.contentToWebContent(content));
        }
        return webContents;
    }
}
