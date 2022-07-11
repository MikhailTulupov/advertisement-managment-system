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

    @Override
    public List<WebContent> saveAll(List<WebContent> webContentList) {
        List<Content> contents = new ArrayList<>();
        for (WebContent webContent : webContentList) {
            Content content = ContentMapper.INSTANCE.webContentToContent(webContent);
            contents.add(content);
        }

        return ContentMapper.INSTANCE.contentsToWebContents(contentService.saveAll(contents));
    }
}