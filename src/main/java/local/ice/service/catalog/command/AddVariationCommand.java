package local.ice.service.catalog.command;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;


public record AddVariationCommand(
        @NotNull @Valid Ref artist,
        @NotEmpty String variation
) implements CatalogCommand {

    //~

}
