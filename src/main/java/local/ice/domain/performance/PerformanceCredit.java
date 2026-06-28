package local.ice.domain.performance;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.util.UUID;


@Table("performance_credit")
public class PerformanceCredit {

    @Id
    private final UUID id;

    @Getter
    private final UUID artistId;

    @Getter
    private final String role;

    public PerformanceCredit(UUID id, UUID artistId, String role) {
        this.id = id;
        this.artistId = artistId;
        this.role = role;
    }

}
