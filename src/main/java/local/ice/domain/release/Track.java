package local.ice.domain.release;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;

@Table("music_release_track")
public class Track {

    @Id
    private final UUID id;

    @Getter
    private final UUID performanceId;

    public Track(UUID id, UUID performanceId) {
        this.id = id;
        this.performanceId = performanceId;
    }

}
