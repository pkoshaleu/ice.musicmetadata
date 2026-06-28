package local.ice.controller.dto;

import java.util.Map;
import java.util.UUID;


public record CatalogResult(
        Map<String, UUID> aliases
) {
    //~
}
