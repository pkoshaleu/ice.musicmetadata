package local.ice.service.release;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import local.ice.domain.release.Release;
import local.ice.repository.ReleaseRepository;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ReleaseSearchService {

    private final ReleaseRepository repository;

    public Optional<Release> getOne(UUID id) {
        return repository.findById(id);
    }

}
