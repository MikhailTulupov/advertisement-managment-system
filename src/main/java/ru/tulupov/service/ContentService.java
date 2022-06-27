package ru.tulupov.service;

import ru.tulupov.model.Content;

import java.util.List;
import java.util.UUID;

/**
 * The {@link ContentService} interface provides a set of methods for manipulating {@link Content} data.
 */
public interface ContentService {
    /**
     * Method save {@link Content} in database.
     *
     * @param content content
     */
    void save(Content content);

    /**
     * Method save {@link List}<{@link Content}> in database.
     *
     * @param contents list of contents
     */
    void saveAll(List<Content> contents);

    /**
     * Method find {@link Content} by id and return content.
     *
     * @param id content id
     * @return {@link Content} instance
     */
    Content getById(UUID id);

    /**
     * Method return {@link List}<{@link Content}>
     *
     * @return list of contents
     */
    List<Content> getAll();
}
