package local.ice.domain.artist;

import lombok.Getter;
import org.jspecify.annotations.Nullable;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;


@Table("artist")
public class Artist implements Persistable<UUID> {

    @Id private final UUID id;
    @Getter private final String title;
    @Getter @Column("artist_type") private final ArtistType type;
    @Getter private final Set<String> variations;

    @Transient
    private boolean isNew;

    @PersistenceCreator
    public Artist(UUID id, String title, ArtistType type, Set<String> variations) {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("Artist must have a title");
        }
        if (type == null) {
            throw new IllegalArgumentException("Artist must have a type");
        }

        this.id = id;
        this.title = title;
        this.type = type;
        this.variations = variations == null
                ? new LinkedHashSet<>()
                : new LinkedHashSet<>(variations);
    }

    @Override
    public @Nullable UUID getId() {
        return id;
    }

    @Override
    public boolean isNew() {
        return isNew;
    }

    public void addVariation(String variation) {
        if (variation == null || variation.isBlank()) {
            throw new IllegalArgumentException("Variation must not be blank");
        }
        String normalized = variation.trim(); //TODO: normalize to NFKC

        boolean exists = title.equalsIgnoreCase(normalized)
                || variations.stream().anyMatch(existing -> existing.equalsIgnoreCase(normalized));

        if (exists) {
            throw new IllegalArgumentException("Artist variation already exists");
        }

        variations.add(normalized);
    }

}
