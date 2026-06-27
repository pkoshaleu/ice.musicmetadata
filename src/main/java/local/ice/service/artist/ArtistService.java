package local.ice.service.artist;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import local.ice.domain.artist.Artist;
import local.ice.domain.artist.ArtistRepository;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ArtistService {

    private final ArtistRepository repository;

    public Optional<Artist> getOne(UUID id) {
        return repository.findById(id);
    }

}
