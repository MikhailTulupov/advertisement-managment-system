package ru.tulupov.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

/**
 * The {@link Viewed} class is a data model that is represented as an entity.
 * Viewer contains a {@link User} id and an id of the {@link Content} that the user viewed.
 * The {@link Viewed} class stores records of the content viewed by the user.
 */
@Data
@NoArgsConstructor
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

    @Column(name = "user_guid")
    private UUID userGuid;

    @Column(name = "content_guid")
    private UUID contentGuid;
}
