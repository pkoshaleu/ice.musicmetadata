package local.ice.domain.release;

import io.github.robsonkades.uuidv7.UUIDv7;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Set;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


class ReleaseTest {

    @Test
    void addCredit_appendsCredit() {
        Release release = newRelease();
        UUID artistId = UUIDv7.randomUUID();

        release.addCredit(artistId, "Main Artist");

        assertThat(release.getCredits()).singleElement().satisfies(credit -> {
            assertThat(credit.getArtistId()).isEqualTo(artistId);
            assertThat(credit.getRole()).isEqualTo("Main Artist");
        });
    }

    @Test
    void addCredit_preservesInsertionOrder() {
        Release release = newRelease();
        UUID a = UUIDv7.randomUUID();
        UUID b = UUIDv7.randomUUID();

        release.addCredit(a, "Main Artist");
        release.addCredit(b, "Featured");

        assertThat(release.getCredits())
                .extracting(ReleaseCredit::getArtistId)
                .containsExactly(a, b);
    }

    @Test
    void addCredit_rejectsNullArtist() {
        Release release = newRelease();

        assertThatThrownBy(() -> release.addCredit(null, "Main Artist"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("must reference an artist");
    }

    private static Release newRelease() {
        return Release.create(
                "Innuendo",
                ReleaseType.ALBUM,
                LocalDate.of(1991, 2, 4),
                Set.of("rock")
        );
    }

}
