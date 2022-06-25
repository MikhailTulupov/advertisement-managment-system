package ru.tulupov.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.tulupov.model.User;

import java.util.UUID;

/**
 * {@link UserRepository} general purpose is to hold type information {@link User},
 * provide exposed CRUD.
 */
@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
}
