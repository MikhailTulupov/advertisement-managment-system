package ru.tulupov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.tulupov.model.Content;
import ru.tulupov.model.Page;

import java.util.List;
import java.util.UUID;

/**
 * {@link PageRepository} general purpose is to hold type information {@link Page},
 * provide exposed CRUD.
 */
@Repository
public interface PageRepository extends JpaRepository<Page, UUID> {
    @Query("SELECT p.contents FROM Page p WHERE p.name = :pageName")
    List<Content> findAllContentByPageName(@Param("pageName") String pageName);
}
