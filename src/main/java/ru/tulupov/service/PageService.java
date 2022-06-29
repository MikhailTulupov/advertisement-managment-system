package ru.tulupov.service;

import ru.tulupov.model.Page;

import java.util.List;

public interface PageService {
    void saveAll(List<Page> pages);
    void save(Page page);
}
