package local.ice.service.catalog.command;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;


public record AddReleaseCreditCommand(
        @NotNull Ref release,
        @Positive int position,
        @NotNull Ref artist,
        @NotEmpty String role
) implements CatalogCommand {

    //~

}
