package local.ice.repository;

import org.springframework.data.repository.CrudRepository;

import local.ice.domain.release.Release;

import java.util.UUID;

public interface ReleaseRepository extends CrudRepository<Release, UUID> {

    //~

}
