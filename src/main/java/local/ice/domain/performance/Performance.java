package local.ice.domain.performance;

import lombok.Getter;
import org.jspecify.annotations.Nullable;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.util.List;
import java.util.Set;
import java.util.UUID;


@Table("performance")
public class Performance implements Persistable<UUID> {

    @Id
    private final UUID id;

    @Getter
    private final UUID compositionId;

    @Getter
    private String title;

    @Getter
    private final int duration;

    @Getter
    @Column("lang")
    private String language;

    @Getter
    private final Set<String> genres;

    @Getter
    @MappedCollection(idColumn = "performance_id", keyColumn = "position")
    private final List<PerformanceCredit> credits;

    @Transient
    private boolean isNew;

    public Performance(
            UUID id,
            UUID compositionId,
            String title,
            int duration,
            String language,
            Set<String> genres,
            List<PerformanceCredit> credits
    ) {
        this.id = id;
        this.compositionId = compositionId;
        this.title = title;
        this.duration = duration;
        this.credits = credits;
        this.genres = genres;
        this.language = language;
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
