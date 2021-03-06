package ru.tulupov.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.tulupov.model.Content;
import ru.tulupov.model.Page;
import ru.tulupov.repository.PageRepository;

import java.util.List;

/**
 * The {@link PageServiceImpl} class implements {@link PageService} interface methods.
 */
@Service
public class PageServiceImpl implements PageService {
    @Autowired
    PageRepository pageRepository;

    @Override
    public List<Page> saveAll(List<Page> pages) {
        return pageRepository.saveAll(pages);
    }

    @Override
    public Page save(Page page) {
        return pageRepository.save(page);
    }

    @Override
    public List<Content> getAllContentByPageName(String pageName) {
        return pageRepository.findAllContentByPageName(pageName);
    }
}