package ru.tulupov.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.tulupov.model.Content;
import ru.tulupov.repository.ContentRepository;
import ru.tulupov.repository.PageRepository;

import java.util.List;
import java.util.UUID;

/**
 * The {@link ContentServiceImpl} class implements {@link ContentService} interface methods.
 */
@Service
public class ContentServiceImpl implements ContentService {
    @Autowired
    ContentRepository contentRepository;
    @Autowired
    PageService pageService;

    @Override
    public Content save(Content content) {
        return contentRepository.save(content);
    }

    @Override
    public List<Content> saveAll(List<Content> contents) {
        contentRepository.saveAll(contents);
        for (Content content : contents) {
            pageService.saveAll(List.copyOf(content.getPages()));
        }
        return contents;
    }

    @Override
    public Content getById(UUID id) {
        return contentRepository.findById(id).orElseGet(Content::new);
    }

    @Override
    public List<Content> getAll() {
        return contentRepository.findAll();
    }
}