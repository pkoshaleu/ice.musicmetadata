package local.ice.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import local.ice.domain.release.Release;
import local.ice.service.release.ReleaseSearchService;

import java.util.UUID;


@RestController
@RequestMapping("/releases")
@AllArgsConstructor
public class ReleaseController {

    private final ReleaseSearchService releaseService;

    @GetMapping("/{id}")
    public ResponseEntity<Release> getOne(
            @PathVariable UUID id
    ) {
        return releaseService.getOne(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
