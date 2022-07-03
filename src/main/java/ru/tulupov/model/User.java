package ru.tulupov.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * The {@link User} class is a data model that is represented as an entity user.
 */
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(exclude = {"viewedSet"})
@Builder
@Entity
@Table(name = "users")
public class User {
    @Id
    private UUID id;

    @ToString.Exclude
    @OneToMany(mappedBy = "user")
    @Builder.Default
    private Set<Viewed> viewedSet = new HashSet<>();

    public User(UUID id) {
        this.id = id;
    }
}
