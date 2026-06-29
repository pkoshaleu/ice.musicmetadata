package local.ice.service.artist;

import io.github.robsonkades.uuidv7.UUIDv7;
import local.ice.domain.artist.Artist;
import local.ice.domain.artist.ArtistType;
import local.ice.repository.ArtistRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class ArtistOtdServiceTest {

    @Mock
    private ArtistRepository repository;

    @Test
    void pickNext_reshufflesAfterDrained() {
        UUID id = UUIDv7.randomUUID();
        when(repository.findAllIds()).thenAnswer(inv -> new ArrayList<>(List.of(id)));
        when(repository.findById(id)).thenReturn(Optional.of(artist(id)));

        ArtistOtdService service = new ArtistOtdService(repository);
        service.pickNext();
        service.pickNext();

        verify(repository, times(2)).findAllIds();
    }

    private static Artist artist(UUID id) {
        return new Artist(id, "Artist " + id, ArtistType.PERSON, Set.of());
    }

}
