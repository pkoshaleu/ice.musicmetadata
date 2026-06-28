package local.ice.repository;

import org.springframework.data.repository.CrudRepository;

import local.ice.domain.performance.Performance;

import java.util.UUID;


public interface PerformanceRepository extends CrudRepository<Performance, UUID> {

    //~

}
