package ru.tulupov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.tulupov.model.Page;

import java.util.UUID;

/**
 * {@link PageRepository} general purpose is to hold type information {@link Page},
 * provide exposed CRUD.
 */
@Repository
public interface PageRepository extends JpaRepository<Page, UUID> {
}
