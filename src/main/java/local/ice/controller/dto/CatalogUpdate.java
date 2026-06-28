package local.ice.controller.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import local.ice.service.catalog.command.CatalogCommand;

import java.util.List;


public record CatalogUpdate(
        @NotEmpty List<CatalogCommand> operations,
        @NotNull Long correlationId
) {
    //~
}
