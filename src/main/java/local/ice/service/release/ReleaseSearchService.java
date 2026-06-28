package local.ice.service.release;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import local.ice.controller.dto.ReleaseSummary;
import local.ice.domain.release.Release;
import local.ice.repository.ReleaseRepository;
import local.ice.service.CreditRoleFilter;

import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Service
@AllArgsConstructor
public class ReleaseSearchService {

    private final ReleaseRepository repository;

    public Optional<Release> getOne(UUID id) {
        return repository.findById(id);
    }

    public List<ReleaseSummary> findCreditedTo(UUID artistId, String role) {
        if (role == null || role.isBlank()) {
            return repository.findAllByPerformingArtist(artistId);
        } else {
            CreditRoleFilter filter = CreditRoleFilter.from(role);
            return repository.findAllCreditedToWithRole(artistId, filter.creditRole());
        }
    }

}
