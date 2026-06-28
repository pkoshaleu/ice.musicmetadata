package local.ice.repository;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import local.ice.controller.dto.PerformanceSummary;
import local.ice.domain.performance.Performance;

import java.util.List;
import java.util.UUID;


public interface PerformanceRepository extends CrudRepository<Performance, UUID> {

    @Query("""
            SELECT
                id
                , composition_id
                , title
                , duration
                , lang AS language
                , genres
            FROM performance
            WHERE id IN (
                SELECT performance_id 
                FROM performance_credit 
                WHERE artist_id = :artistId
            )
            ORDER BY title
            """)
    List<PerformanceSummary> findAllCreditedTo(@Param("artistId") UUID artistId);

    @Query("""
            SELECT
                id
                , composition_id
                , title
                , duration
                , lang AS language
                , genres
            FROM performance
            WHERE id IN (
                SELECT performance_id
                FROM performance_credit
                WHERE artist_id = :artistId AND role = :role
            )
            ORDER BY title
            """)
    List<PerformanceSummary> findAllCreditedToWithRole(
            @Param("artistId") UUID artistId,
            @Param("role") String role
    );

}
