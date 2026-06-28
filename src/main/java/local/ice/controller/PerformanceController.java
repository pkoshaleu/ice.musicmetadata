package local.ice.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import local.ice.domain.performance.Performance;
import local.ice.service.PerformanceSearchService;

import java.util.UUID;


@RestController
@RequestMapping("/performances")
@AllArgsConstructor
public class PerformanceController {

    private final PerformanceSearchService performanceService;

    @GetMapping("/{id}")
    public ResponseEntity<Performance> getOne(
            @PathVariable UUID id
    ) {
        return performanceService.getOne(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
