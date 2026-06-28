package local.ice.domain.release;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;


@Table("music_release_credit")
public class ReleaseCredit {

    @Id
    private final UUID id;

    @Getter
    private final UUID artistId;

    @Getter
    private final String role;

    public ReleaseCredit(
            UUID id,
            UUID artistId,
            String role
    ) {
        this.id = id;
        this.artistId = artistId;
        this.role = role;
    }

}
