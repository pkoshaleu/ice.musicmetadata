package local.ice.service.catalog;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import local.ice.service.artist.ArtistUpdateService;
import local.ice.service.catalog.command.AddReleaseCommand;
import local.ice.service.catalog.command.AddReleaseCreditCommand;
import local.ice.service.catalog.command.AddTrackCommand;
import local.ice.service.catalog.command.AddVariationCommand;
import local.ice.service.catalog.command.CatalogCommand;
import local.ice.controller.dto.CatalogResult;
import local.ice.service.release.ReleaseUpdateService;

import java.util.List;


@Service
@AllArgsConstructor
public class CatalogService {

    private final ArtistUpdateService artistService;
    private final ReleaseUpdateService releaseService;

    @Transactional
    public CatalogResult replay(List<CatalogCommand> operations) {
        Context context = new Context();

        for (CatalogCommand operation : operations) {
            switch (operation) {
                case AddVariationCommand command -> artistService.addVariation(command, context);
                case AddReleaseCommand command -> releaseService.addRelease(command, context);
                case AddReleaseCreditCommand command -> releaseService.addReleaseCredit(command, context);
                case AddTrackCommand command -> releaseService.addTrack(command, context);
            }
        }

        return new CatalogResult(context.aliases());
    }

}
