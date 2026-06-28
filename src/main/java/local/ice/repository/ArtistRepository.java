package local.ice.repository;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;

import local.ice.domain.artist.Artist;

import java.util.List;
import java.util.UUID;


public interface ArtistRepository extends CrudRepository<Artist, UUID> {

    @Query("SELECT id FROM artist")
    List<UUID> findAllIds();

}
