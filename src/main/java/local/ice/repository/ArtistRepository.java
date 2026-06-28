package local.ice.repository;

import org.springframework.data.repository.CrudRepository;

import local.ice.domain.artist.Artist;

import java.util.UUID;


public interface ArtistRepository extends CrudRepository<Artist, UUID> {

    //~

}
