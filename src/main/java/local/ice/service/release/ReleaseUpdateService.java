package local.ice.service.release;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import local.ice.domain.NotFoundException;
import local.ice.domain.release.Release;
import local.ice.repository.ArtistRepository;
import local.ice.repository.PerformanceRepository;
import local.ice.repository.ReleaseRepository;
import local.ice.service.catalog.Context;
import local.ice.service.catalog.command.AddReleaseCommand;
import local.ice.service.catalog.command.AddReleaseCreditCommand;
import local.ice.service.catalog.command.AddTrackCommand;

import java.util.UUID;

@Service
@AllArgsConstructor
public class ReleaseUpdateService {

    private final ReleaseRepository releases;
    private final ArtistRepository artists;
    private final PerformanceRepository performances;

    @Transactional
    public void addRelease(AddReleaseCommand command, Context context) {
        Release release = Release.create(
                command.title(),
                command.releaseType(),
                command.releaseDate(),
                command.genres()
        );

        Release saved = releases.save(release);
        context.bind(command.alias(), saved.getId());
    }

    @Transactional
    public void addReleaseCredit(AddReleaseCreditCommand command, Context context) {
        UUID releaseId = context.resolve(command.release());
        Release release = releases.findById(releaseId)
                .orElseThrow(() -> new NotFoundException("Release not found; id=" + releaseId));

        UUID artistId = context.resolve(command.artist());
        if (!artists.existsById(artistId)) {
            throw new NotFoundException("Artist not found; id=" + artistId);
        }

        release.addCredit(artistId, command.role());
        releases.save(release);
    }

    @Transactional
    public void addTrack(AddTrackCommand command, Context context) {
        UUID releaseId = context.resolve(command.release());
        Release release = releases.findById(releaseId)
                .orElseThrow(() -> new NotFoundException("Release not found; id=" + releaseId));

        UUID performanceId = context.resolve(command.performance());
        if (!performances.existsById(performanceId)) {
            throw new NotFoundException("Performance not found; id=" + performanceId);
        }

        release.addTrack(performanceId, command.position());
        releases.save(release);
    }

}
