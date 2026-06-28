package local.ice.domain.release;

import io.github.robsonkades.uuidv7.UUIDv7;
import lombok.Getter;
import org.jspecify.annotations.Nullable;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;


@Table("music_release")
public class Release implements Persistable<UUID> {

    @Id
    private final UUID id;

    @Getter
    private String title;

    @Getter
    private ReleaseType releaseType;

    @Getter
    private LocalDate releaseDate;

    @Getter
    private final Set<String> genres;

    @Getter
    @MappedCollection(idColumn = "release_id", keyColumn = "position")
    private final List<ReleaseCredit> credits;

    @Getter
    @MappedCollection(idColumn = "release_id", keyColumn = "position")
    private final List<Track> tracks;

    @Transient
    private boolean isNew;

    @PersistenceCreator
    public Release(
            UUID id,
            String title,
            ReleaseType releaseType,
            LocalDate releaseDate,
            Set<String> genres,
            List<ReleaseCredit> credits,
            List<Track> tracks
    ) {
        //TODO: validation
        this.id = id;
        this.title = title;
        this.releaseType = releaseType;
        this.releaseDate = releaseDate;
        this.genres = genres == null ? new LinkedHashSet<>() : new LinkedHashSet<>(genres);
        this.credits = credits == null ? new ArrayList<>() : new ArrayList<>(credits);
        this.tracks = tracks == null ? new ArrayList<>() : new ArrayList<>(tracks);
    }

    public static Release create(
            String title,
            ReleaseType releaseType,
            LocalDate releaseDate,
            Set<String> genres
    ) {
        Release release = new Release(
                UUIDv7.randomUUID(),
                title,
                releaseType,
                releaseDate,
                genres == null ? new LinkedHashSet<>() : genres,
                new ArrayList<>(),
                new ArrayList<>()
        );

        release.isNew = true;
        return release;
    }

    public void addCredit(UUID artistId, String role) {
        if (artistId == null) {
            throw new IllegalArgumentException("Release credit must reference an artist");
        }

        if (role == null || role.isBlank()) {
            throw new IllegalArgumentException("Release credit role must not be blank");
        }

        credits.add(new ReleaseCredit(UUIDv7.randomUUID(), artistId, role));
    }

    public void addTrack(UUID performanceId, int position) {
        if (performanceId == null) {
            throw new IllegalArgumentException("Track must reference a performance");
        }
        tracks.add(new Track(UUIDv7.randomUUID(), performanceId));
    }

    @Override
    public @Nullable UUID getId() {
        return id;
    }

    @Override
    public boolean isNew() {
        return isNew;
    }

}
