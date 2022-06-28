package ru.tulupov.service.web;

import ru.tulupov.model.Content;
import ru.tulupov.model.web.WebContent;
import ru.tulupov.service.ContentService;

import java.util.List;

/**
 * The {@link WebContentService} interface provides a set of methods for manipulating {@link WebContent} data.
 */
public interface WebContentService {
    /**
     * Method save {@link WebContent} in database.
     *
     * @param webContent web content
     */
    void save(WebContent webContent);

    /**
     * Method save {@link List}<{@link WebContent}> in database.
     *
     * @param webContentList list of web contents
     */
    void saveAll(List<WebContent> webContentList);

    /**
     * Method find {@link WebContent} by id and return content.
     *
     * @param uuid content id
     * @return {@link WebContent} instance
     */
    WebContent getById(String uuid);

    /**
     * Method return {@link List}<{@link WebContent}>
     *
     * @return list of web contents
     */
    List<WebContent> getAll();
}
