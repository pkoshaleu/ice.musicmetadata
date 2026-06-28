package local.ice.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import local.ice.controller.dto.CatalogResult;
import local.ice.controller.dto.CatalogUpdate;
import local.ice.service.catalog.CatalogService;


@RestController
@RequestMapping("/catalog")
@AllArgsConstructor
public class CatalogController {

    private final CatalogService catalogService;

    @PostMapping("/update")
    public ResponseEntity<CatalogResult> update(
            @Valid @RequestBody CatalogUpdate submission
    ) {
        return ResponseEntity.ok(catalogService.replay(submission.operations()));
    }

}
