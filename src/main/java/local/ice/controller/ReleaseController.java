package local.ice.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import local.ice.controller.dto.ReleaseSummary;
import local.ice.controller.dto.ReleaseView;
import local.ice.service.release.ReleaseSearchService;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/releases")
@AllArgsConstructor
public class ReleaseController {

    private final ReleaseSearchService releaseService;

    @GetMapping("/{id}")
    public ResponseEntity<ReleaseView> getOne(
            @PathVariable UUID id
    ) {
        return releaseService.getOne(id)
                .map(ReleaseView::map)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<ReleaseSummary> findCreditedTo(
            @RequestParam UUID artist,
            @RequestParam(required = false) String role
    ) {
        return releaseService.findCreditedTo(artist, role);
    }

}
