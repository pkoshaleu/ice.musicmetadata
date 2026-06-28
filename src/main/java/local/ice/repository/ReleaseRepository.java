package local.ice.repository;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import local.ice.controller.dto.ReleaseSummary;
import local.ice.domain.release.Release;

import java.util.List;
import java.util.UUID;


public interface ReleaseRepository extends CrudRepository<Release, UUID> {

    @Query("""
            SELECT
                id
                , title
                , release_type
                , release_date
                , genres
            FROM music_release
            WHERE id IN (
                SELECT t.release_id
                FROM music_release_track t
                JOIN performance_credit pc ON pc.performance_id = t.performance_id
                WHERE pc.artist_id = :artistId
            )
            ORDER BY title
            """)
    List<ReleaseSummary> findAllByPerformingArtist(@Param("artistId") UUID artistId);

    @Query("""
            SELECT
                id
                , title
                , release_type
                , release_date
                , genres
            FROM music_release
            WHERE id IN (
                SELECT release_id
                FROM music_release_credit
                WHERE artist_id = :artistId AND role = :role
            )
            ORDER BY title
            """)
    List<ReleaseSummary> findAllCreditedToWithRole(
            @Param("artistId") UUID artistId,
            @Param("role") String role
    );

}
