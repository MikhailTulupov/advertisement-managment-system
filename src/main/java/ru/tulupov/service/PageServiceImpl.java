package ru.tulupov.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.tulupov.model.Page;
import ru.tulupov.repository.PageRepository;

import java.util.List;

@Service
public class PageServiceImpl implements PageService{
    @Autowired
    PageRepository pageRepository;

    @Override
    public void saveAll(List<Page> pages) {
        pageRepository.saveAll(pages);
    }

    @Override
    public void save(Page page) {
        pageRepository.save(page);
    }
}
