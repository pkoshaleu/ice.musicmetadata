package local.ice.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import local.ice.domain.artist.Artist;
import local.ice.service.artist.AddVariationCommand;
import local.ice.service.artist.ArtistService;

import java.util.UUID;

@RestController
@RequestMapping("/artists")
@AllArgsConstructor
public class ArtistController {

    private final ArtistService artistService;
    private final AddVariationCommand addVariation;

    @GetMapping("/{id}")
    public ResponseEntity<ArtistView> getOne(
            @PathVariable UUID id
    ) {
        return artistService.getOne(id)
                .map(ArtistView::map)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/{id}/variations")
    public ResponseEntity<ArtistView> addVariation(
            @PathVariable UUID id,
            @Valid @RequestBody AddVariationForm form
    ) {
        Artist artist = addVariation.addVariation(
                id,
                form.variation()
        );

        return ResponseEntity.ok(ArtistView.map(artist));
    }

    //TODO: search by variation/title

    public record AddVariationForm (
            @NotBlank @Size(min = 1) String variation
    ) {}

}
