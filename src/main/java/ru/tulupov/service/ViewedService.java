package ru.tulupov.service;

import ru.tulupov.model.Content;
import ru.tulupov.model.User;
import ru.tulupov.model.Viewed;

import java.util.List;
import java.util.UUID;

/**
 * The {@link ViewedService} interface provides a set of methods for manipulating {@link Viewed} data.
 */
public interface ViewedService {
    /**
     * Method save {@link Viewed} in database.
     *
     * @param viewed viewed
     */
    Viewed save(Viewed viewed);

    /**
     * Method save {@link List}<{@link Viewed}> in database.
     *
     * @param viewedList list of viewed
     */
    List<Viewed> saveAll(List<Viewed> viewedList);

    /**
     * Method find {@link Viewed} by id and return viewed.
     *
     * @param id content id
     * @return {@link Viewed} instance
     */
    Viewed getById(UUID id);

    /**
     * Method return {@link List}<{@link Viewed}>
     *
     * @return list of viewed.
     */
    List<Viewed> getAll();

    /**
     * Method finds all users who viewed content by current content id.
     *
     * @param id content id
     * @return list of users
     */
    List<User> findAllUsersByContentId(UUID id);

    /**
     * Method finds all viewed content by current user id.
     *
     * @param id user id
     * @return list of content
     */
    List<Content> findAllContentByUserId(UUID id);
}
