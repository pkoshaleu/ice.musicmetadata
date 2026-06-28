package local.ice.service.catalog;

import local.ice.service.catalog.command.Ref;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


public final class Context {

    private final Map<String, UUID> aliases = new HashMap<>();

    public void bind(String alias, UUID id) {
        if (alias == null || alias.isBlank()) {
            return;
        }
        if (aliases.putIfAbsent(alias, id) != null) {
            throw new IllegalArgumentException("Duplicate alias; alia=" + alias);
        }
    }

    public UUID resolve(Ref ref) {
        if (ref.id() != null) {
            return ref.id();
        }
        if (ref.alias() == null || ref.alias().isBlank()) {
            throw new IllegalArgumentException("Reference must have an alias or an id");
        }
        if (!aliases.containsKey(ref.alias())) {
            throw new IllegalArgumentException("Alias has no binding to id; alias=" + ref.alias());
        }

        return aliases.get(ref.alias());
    }

    public Map<String, UUID> aliases() {
        return Map.copyOf(aliases);
    }

}
