package local.ice.controller.dto;

import local.ice.domain.artist.Artist;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public record ArtistView (
        UUID id,
        String title,
        String type,
        List<String> variations
) {

    public static ArtistView map(Artist artist) {
        return new ArtistView(
                artist.getId(),
                artist.getTitle(),
                artist.getType().name().toLowerCase(),
                new ArrayList<>(artist.getVariations())
        );
    }
}
