package local.ice.service.catalog.command;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.jspecify.annotations.Nullable;

import local.ice.domain.release.ReleaseType;

import java.time.LocalDate;
import java.util.Set;


public record AddReleaseCommand(
        @Nullable String alias,
        @NotBlank String title,
        @NotNull ReleaseType releaseType,
        @NotNull LocalDate releaseDate,
        @Nullable Set<String> genres
) implements CatalogCommand {

    //~

}
