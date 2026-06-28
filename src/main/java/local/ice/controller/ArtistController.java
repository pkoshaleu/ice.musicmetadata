package local.ice.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import local.ice.controller.dto.ArtistView;
import local.ice.service.artist.ArtistOtdService;
import local.ice.service.artist.ArtistSearchService;

import java.util.UUID;

@RestController
@RequestMapping("/artists")
@AllArgsConstructor
public class ArtistController {

    private final ArtistSearchService artistService;
    private final ArtistOtdService otdService;

    @GetMapping("/{id}")
    public ResponseEntity<ArtistView> getOne(
            @PathVariable UUID id
    ) {
        return artistService.getOne(id)
                .map(ArtistView::map)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/artist-of-the-day")
    public ResponseEntity<ArtistView> artistOfTheDay() {
        return otdService.pickNext()
                .map(ArtistView::map)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

}
