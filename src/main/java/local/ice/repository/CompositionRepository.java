package local.ice.repository;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import local.ice.controller.dto.CompositionSummary;
import local.ice.domain.composition.Composition;

import java.util.List;
import java.util.UUID;


public interface CompositionRepository extends CrudRepository<Composition, UUID> {

    @Query("""
            SELECT
                id
                , title
                , lang AS language
            FROM composition
            WHERE id IN (
                SELECT composition_id FROM composition_credit WHERE artist_id = :artistId
            )
            ORDER BY title
            """)
    List<CompositionSummary> findAllCreditedTo(@Param("artistId") UUID artistId);

}
