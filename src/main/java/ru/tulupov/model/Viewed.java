package ru.tulupov.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

/**
 * The {@link Viewed} class is a data model that is represented viewed.
 * Viewer contains a {@link User} id who watch viewed content and an id of the {@link Content}.
 * The {@link Viewed} class stores records of the content viewed by the user.
 */
@AllArgsConstructor
@Builder
@Data
@Entity
@RequiredArgsConstructor
@Table(name = "viewed")
public class Viewed {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_guid")
    private User user;

    @ManyToOne
    @JoinColumn(name = "content_guid")
    private Content content;
}