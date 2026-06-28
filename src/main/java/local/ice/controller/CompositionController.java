package local.ice.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import local.ice.domain.composition.Composition;
import local.ice.service.CompositionSearchService;

import java.util.UUID;


@RestController
@RequestMapping("/compositions")
@AllArgsConstructor
public class CompositionController {

    private final CompositionSearchService searchService;

    @GetMapping("/{id}")
    public ResponseEntity<Composition> getOne(
            @PathVariable UUID id
    ) {
        return searchService.getOne(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
