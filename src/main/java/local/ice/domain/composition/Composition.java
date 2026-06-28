package local.ice.domain.composition;

import lombok.Getter;
import org.jspecify.annotations.Nullable;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceCreator;
import org.springframework.data.annotation.Transient;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.MappedCollection;
import org.springframework.data.relational.core.mapping.Table;

import java.util.List;
import java.util.UUID;


@Table("composition")
public class Composition implements Persistable<UUID> {

    @Id
    private final UUID id;

    @Getter
    private String title;

    @Getter
    @Column("lang")
    private String language;

    @Getter
    @MappedCollection(idColumn = "composition_id", keyColumn = "position")
    private final List<CompositionCredit> credits;

    @Transient
    private boolean isNew;

    @PersistenceCreator
    public Composition(
            UUID id,
            String title,
            String language,
            List<CompositionCredit> credits
    ) {
        //TODO: validate
        this.id = id;
        this.title = title;
        this.language = language;
        this.credits = credits;
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
