package local.ice.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import local.ice.controller.dto.CompositionSummary;
import local.ice.controller.dto.CompositionView;
import local.ice.service.CompositionSearchService;

import java.util.List;
import java.util.UUID;


@RestController
@RequestMapping("/compositions")
@AllArgsConstructor
public class CompositionController {

    private final CompositionSearchService searchService;

    @GetMapping("/{id}")
    public ResponseEntity<CompositionView> getOne(
            @PathVariable UUID id
    ) {
        return searchService.getOne(id)
                .map(CompositionView::map)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<CompositionSummary> findCreditedTo(
            @RequestParam UUID artist
    ) {
        return searchService.findCreditedTo(artist);
    }

}
