package ru.tulupov.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

/**
 * The {@link Page} class is a data model that is represented as an entity.
 * Page contains the name of the page and a many-to-many
 * relationship with the {@link Content} class. The many-to-many relationship
 * with {@link Content} is represented as {@link Set} <{@link Content}>, i.e.
 * a {@link Page} can contain a {@link Set} of {@link Content},
 * just as a {@link Content} can be contained on a {@link Set} of {@link Page}.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "page")
public class Page {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @Column
    private String name;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "page_content",
            joinColumns = @JoinColumn(name = "page_id"),
            inverseJoinColumns = @JoinColumn(name = "content_id"))
    @Builder.Default
    private Set<Content> contents = new HashSet<>();
}
