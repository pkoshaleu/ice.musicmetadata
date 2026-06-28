package local.ice.service;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import local.ice.domain.performance.Performance;
import local.ice.repository.PerformanceRepository;

import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class PerformanceSearchService {

    private final PerformanceRepository repository;

    public Optional<Performance> getOne(UUID id) {
        return repository.findById(id);
    }

}
