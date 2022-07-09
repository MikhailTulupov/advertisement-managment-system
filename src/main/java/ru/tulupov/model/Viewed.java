package ru.tulupov.model;

import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

/**
 * The {@link Viewed} class is a data model that is represented as an entity.
 * Viewer contains a {@link User} id and an id of the {@link Content} that the user viewed.
 * The {@link Viewed} class stores records of the content viewed by the user.
 */
@Data
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "viewed")
public class Viewed {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator")
    private UUID id;

    @ManyToOne(fetch = FetchType.EAGER,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    @JoinColumn(name = "user_guid", referencedColumnName = "id")
    private User user;

   @ManyToOne(fetch = FetchType.EAGER)
   @JoinColumn(name = "content_guid", referencedColumnName = "id")
    private Content content;
}
