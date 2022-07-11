package ru.tulupov.service.web;

import ru.tulupov.model.web.WebContent;

import java.util.List;

/**
 * The {@link WebContentService} interface provides a set of methods for manipulating {@link WebContent} data.
 */
public interface WebContentService {
    /**
     * Method save {@link List}<{@link WebContent}> in database.
     *
     * @param webContentList list of web contents
     * @return list save entities.
     */
    List<WebContent> saveAll(List<WebContent> webContentList);
}