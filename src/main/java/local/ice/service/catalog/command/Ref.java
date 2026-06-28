package local.ice.service.catalog.command;

import org.jspecify.annotations.Nullable;

import java.util.UUID;


public record Ref(
        @Nullable String alias,
        @Nullable UUID id
) {

    public Ref(String alias, UUID id) {
        if (alias == null && id == null) {
            throw new IllegalArgumentException("One of Ref should be not null");
        }
        this.alias = alias;
        this.id = id;
    }

}
