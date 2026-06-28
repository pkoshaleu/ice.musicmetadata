package local.ice.controller.dto;

import java.util.Set;
import java.util.UUID;


public record PerformanceSummary(
        UUID id,
        UUID compositionId,
        String title,
        int duration,
        String language,
        Set<String> genres
) {

    //~

}
