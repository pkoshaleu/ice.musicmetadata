package local.ice.domain.artist;

import org.springframework.data.repository.CrudRepository;

import java.util.UUID;


public interface ArtistRepository extends CrudRepository<Artist, UUID> {



   /*
       @Query("""
            SELECT
                id,
                title,
                artist_type AS type,
                variations,
                valid_from,
                valid_until
            FROM artist
            WHERE lower(title) = lower(:title)
               OR variations @> to_jsonb(ARRAY[:title])
            """)
    List<ArtistView> findByTitleOrVariation(String title);
    */
}
