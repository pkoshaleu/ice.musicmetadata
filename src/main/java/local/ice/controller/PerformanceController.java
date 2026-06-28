package local.ice.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import local.ice.controller.dto.PerformanceSummary;
import local.ice.controller.dto.PerformanceView;
import local.ice.service.PerformanceSearchService;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/performances")
@AllArgsConstructor
public class PerformanceController {

    private final PerformanceSearchService performanceService;

    @GetMapping("/{id}")
    public ResponseEntity<PerformanceView> getOne(
            @PathVariable UUID id
    ) {
        return performanceService.getOne(id)
                .map(PerformanceView::map)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<PerformanceSummary> findCreditedTo(
            @RequestParam UUID artist,
            @RequestParam(required = false) String role
    ) {
        return performanceService.findCreditedTo(artist, role);
    }

}
