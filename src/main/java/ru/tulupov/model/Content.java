package ru.tulupov.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * The {@link Content} class is a data model that is represented as an entity.
 * Content contains many-to-many relationship with the {@link Page} class.
 * The many-to-many relationship with a Page is represented as a {@link Set}<{@link Page}>,
 * i.e. {@link Content} can be contained on many pages. And a {@link Page} can contain {@link Set}<{@link Content}>.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "content")
public class Content {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @ManyToMany(mappedBy = "contents")
    @Builder.Default
    private Set<Page> pages = new HashSet<>();

    @OneToMany(mappedBy = "content")
    @Builder.Default
    private Set<Viewed> viewedSet = new HashSet<>();


    public Content(UUID id) {
        this.id = id;
    }
}
