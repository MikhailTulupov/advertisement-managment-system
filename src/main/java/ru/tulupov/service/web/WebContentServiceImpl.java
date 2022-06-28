package ru.tulupov.service.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.tulupov.mapper.ContentMapper;
import ru.tulupov.model.Content;
import ru.tulupov.model.Page;
import ru.tulupov.model.web.WebContent;
import ru.tulupov.service.ContentService;
import ru.tulupov.service.PageService;

import java.util.*;

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
    public WebContent save(WebContent webContent) {
        Content content = ContentMapper.INSTANCE.webContentToContent(webContent);
        Set<Page> pages = content.getPages();
        for (Page page : pages) {
            page.getContents().add(content);
        }
        return ContentMapper.INSTANCE.contentToWebContent(contentService.save(content));
    }

    @Override
    public List<WebContent> saveAll(List<WebContent> webContentList) {
        List<Content> contents = new ArrayList<>();
        for (WebContent webContent : webContentList) {
            Content content = ContentMapper.INSTANCE.webContentToContent(webContent);
            contents.add(content);
            Set<Page> pages = content.getPages();

            for (Page page : pages) {
                page.getContents().add(content);
            }
        }

        return ContentMapper.INSTANCE.contentsToWebContents(contentService.saveAll(contents));
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
        for (Content content : contents) {
            webContents.add(ContentMapper.INSTANCE.contentToWebContent(content));
        }
        return webContents;
    }
}
