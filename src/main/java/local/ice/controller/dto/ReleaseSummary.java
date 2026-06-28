package local.ice.controller.dto;

import local.ice.domain.release.ReleaseType;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;


public record ReleaseSummary(
        UUID id,
        String title,
        ReleaseType releaseType,
        LocalDate releaseDate,
        Set<String> genres
) {

    //~

}
