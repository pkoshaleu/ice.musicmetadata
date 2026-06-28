package local.ice.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import local.ice.domain.composition.Composition;
import local.ice.repository.CompositionRepository;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class CompositionSearchService {

    private final CompositionRepository repository;

    public Optional<Composition> getOne(UUID id) {
        return repository.findById(id);
    }

}
