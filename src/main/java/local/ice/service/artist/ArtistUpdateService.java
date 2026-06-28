package local.ice.service.artist;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import local.ice.domain.NotFoundException;
import local.ice.domain.artist.Artist;
import local.ice.repository.ArtistRepository;
import local.ice.service.catalog.Context;
import local.ice.service.catalog.command.AddVariationCommand;

import java.util.UUID;


@Service
@AllArgsConstructor
public class ArtistUpdateService {

    private final ArtistRepository repository;

    @Transactional
    public Artist addVariation(AddVariationCommand command, Context context) {
        UUID artistId = context.resolve(command.artist());

        Artist artist = repository.findById(artistId)
                .orElseThrow(() -> new NotFoundException("Artist not found; id=" + artistId));
        artist.addVariation(command.variation());

        return repository.save(artist);
    }

}
