package ru.tulupov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.tulupov.model.Content;
import ru.tulupov.model.User;
import ru.tulupov.model.Viewed;

import java.util.List;
import java.util.UUID;

/**
 * General purpose {@link ViewedRepository} is to hold type information {@link Viewed},
 * provide exposed CRUD.
 */
@Repository
public interface ViewedRepository extends JpaRepository<Viewed, UUID> {
    /**
     * Method finds all users witch viewed content.
     *
     * @param id content identifier
     * @return list of users
     */
    @Query("SELECT v.user FROM Viewed v WHERE v.content.id = :id")
    List<User> findAllUsersByContentId(@Param("id") UUID id);

    /**
     * Method finds all contents witch user viewed.
     *
     * @param id user identifier
     * @return list of contents
     */
    @Query("SELECT v.content FROM Viewed v WHERE v.user.id = :id")
    List<Content> findAllContentByUserId(@Param("id") UUID id);

    /**
     * Method finds all not viewed content by user id
     *
     * @param id user identifier
     * @return list of content
     */
    @Query("SELECT v.content FROM Viewed v WHERE v.user.id <> :id")
    List<Content> findAllNotViewedContentByUserId(@Param("id") UUID id);
}