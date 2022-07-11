package ru.tulupov.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * The {@link User} class is a model that represents a user.
 */
@AllArgsConstructor
@Builder
@Data
@EqualsAndHashCode(exclude = {"viewedSet"})
@Entity
@RequiredArgsConstructor
@Table(name = "users")
public class User {
    @Id
    private UUID id;

    @ToString.Exclude
    @OneToMany(mappedBy = "user")
    @Builder.Default
    private Set<Viewed> viewedSet = new HashSet<>();
}