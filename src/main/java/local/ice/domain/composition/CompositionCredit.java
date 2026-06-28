package local.ice.domain.composition;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;


@Table("composition_credit")
public class CompositionCredit {

    @Id
    private final UUID id;

    @Getter
    private final UUID artistId;

    @Getter
    private final String role;

    public CompositionCredit(UUID id, UUID artistId, String role) {
        this.id = id;
        this.artistId = artistId;
        this.role = role;
    }

}
