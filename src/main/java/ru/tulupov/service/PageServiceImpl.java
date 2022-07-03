package ru.tulupov.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.tulupov.model.Page;
import ru.tulupov.repository.PageRepository;

import java.util.List;
import java.util.Set;

@Service
public class PageServiceImpl implements PageService{
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
}