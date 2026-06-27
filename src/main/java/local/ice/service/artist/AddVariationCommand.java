package local.ice.service.artist;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import local.ice.domain.artist.Artist;
import local.ice.domain.artist.ArtistRepository;

import java.util.UUID;

@Service
@AllArgsConstructor
public class AddVariationCommand {

    private final ArtistRepository repository;

    @Transactional
    public Artist addVariation(UUID artistId, String variation) {
        Artist artist = repository.findById(artistId)
                .orElseThrow(() -> new RuntimeException("Not Found -- IMPLEMENT ME!"));
        artist.addVariation(variation);
        return repository.save(artist);
    }

}
