package local.ice.controller.dto;

import local.ice.domain.release.Release;
import local.ice.domain.release.ReleaseType;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.UUID;


public record ReleaseView(
        UUID id,
        String title,
        ReleaseType releaseType,
        LocalDate releaseDate,
        Set<String> genres,
        List<CreditView> credits,
        List<TrackView> tracks
) {

    public static ReleaseView map(Release release) {
        List<CreditView> credits = release.getCredits().stream()
                .map(credit -> new CreditView(credit.getArtistId(), credit.getRole()))
                .toList();

        List<TrackView> tracks = release.getTracks().stream()
                .map(track -> new TrackView(track.getPerformanceId()))
                .toList();

        return new ReleaseView(
                release.getId(),
                release.getTitle(),
                release.getReleaseType(),
                release.getReleaseDate(),
                release.getGenres(),
                credits,
                tracks
        );
    }
}
