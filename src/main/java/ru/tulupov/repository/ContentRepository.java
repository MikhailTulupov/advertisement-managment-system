package ru.tulupov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.tulupov.model.Content;

import java.util.UUID;

/**
 * General purpose of {@link ContentRepository} is to hold type information {@link Content},
 * provide exposed CRUD methods.
 */
@Repository
public interface ContentRepository extends JpaRepository<Content, UUID> {
}