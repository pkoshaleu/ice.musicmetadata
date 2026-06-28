package local.ice.controller.dto;

import local.ice.domain.composition.Composition;

import java.util.List;
import java.util.UUID;


public record CompositionView(
        UUID id,
        String title,
        String language,
        List<CreditView> credits
) {

    public static CompositionView map(Composition composition) {
        List<CreditView> credits = composition.getCredits().stream()
                .map(credit -> new CreditView(credit.getArtistId(), credit.getRole()))
                .toList();

        return new CompositionView(
                composition.getId(),
                composition.getTitle(),
                composition.getLanguage(),
                credits
        );
    }
}
