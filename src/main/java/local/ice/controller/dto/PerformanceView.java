package local.ice.controller.dto;

import local.ice.domain.performance.Performance;

import java.util.List;
import java.util.Set;
import java.util.UUID;


public record PerformanceView(
        UUID id,
        UUID compositionId,
        String title,
        int duration,
        String language,
        Set<String> genres,
        List<CreditView> credits
) {

    public static PerformanceView map(Performance performance) {
        List<CreditView> credits = performance.getCredits().stream()
                .map(credit -> new CreditView(credit.getArtistId(), credit.getRole()))
                .toList();

        return new PerformanceView(
                performance.getId(),
                performance.getCompositionId(),
                performance.getTitle(),
                performance.getDuration(),
                performance.getLanguage(),
                performance.getGenres(),
                credits
        );
    }
}
