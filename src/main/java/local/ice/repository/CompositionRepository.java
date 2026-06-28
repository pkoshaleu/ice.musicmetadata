package local.ice.repository;

import org.springframework.data.repository.CrudRepository;


import local.ice.domain.composition.Composition;

import java.util.UUID;


public interface CompositionRepository extends CrudRepository<Composition, UUID> {

    //~

}
