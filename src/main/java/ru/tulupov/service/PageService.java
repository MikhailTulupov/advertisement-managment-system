package ru.tulupov.service;

import ru.tulupov.model.Page;

import java.util.List;

public interface PageService {
    /**
     * Method save list of entities.
     *
     * @param pages list of pages.
     * @return list saves entities.
     */
    List<Page> saveAll(List<Page> pages);

    /**
     * Method save entity.
     *
     * @param page page
     * @return save entity.
     */
    Page save(Page page);
}
