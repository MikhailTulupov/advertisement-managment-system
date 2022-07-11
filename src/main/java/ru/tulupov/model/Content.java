package ru.tulupov.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * The {@link Content} class is model that is represented content witch presents in web browser page.
 * Content contains many-to-many relationship with the {@link Page} class.
 * The many-to-many relationship with a Page is represented as a {@link Set}<{@link Page}>,
 * i.e. {@link Content} can be contained on many pages. And a {@link Page} can contain {@link Set}<{@link Content}>.
 * Content also contains one-to-may relationship with the {@link Viewed} class.
 */
@AllArgsConstructor
@Builder
@Data
@EqualsAndHashCode(exclude = {"pages", "viewedSet"})
@Entity
@RequiredArgsConstructor
@Table(name = "content")
public class Content {
    @Id
    private UUID id;

    @ToString.Exclude
    @ManyToMany(mappedBy = "contents", fetch = FetchType.EAGER)
    @Builder.Default
    private Set<Page> pages = new HashSet<>();

    @ToString.Exclude
    @OneToMany(mappedBy = "content")
    @Builder.Default
    private Set<Viewed> viewedSet = new HashSet<>();
}