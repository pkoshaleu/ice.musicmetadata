package local.ice.controller.dto;

import java.util.UUID;


public record CompositionSummary(
        UUID id,
        String title,
        String language
) {

    //~

}
