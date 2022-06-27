package ru.tulupov.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.tulupov.model.Content;
import ru.tulupov.repository.ContentRepository;

import java.util.List;
import java.util.UUID;

/**
 * The {@link ContentServiceImpl} class implements {@link ContentService} methods.
 */
@Service
@AllArgsConstructor
public class ContentServiceImpl implements ContentService {
    final ContentRepository contentRepository;

    @Override
    public void save(Content content) {
        contentRepository.save(content);
    }

    @Override
    public void saveAll(List<Content> contents) {
        contentRepository.saveAll(contents);
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
