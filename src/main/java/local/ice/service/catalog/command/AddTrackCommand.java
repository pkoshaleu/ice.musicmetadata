package local.ice.service.catalog.command;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;


public record AddTrackCommand(
        @NotNull Ref release,
        @Positive int position,
        @NotNull Ref performance
) implements CatalogCommand {

    //~

}
