package local.ice.domain.artist;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;


class ArtistTest {

    @ParameterizedTest
    @ValueSource(strings = {
            "queen",
            "QUEEN",
            "  Queen  "
    })
    void addVariation_rejectsVariationMatchingTitle(String duplicate) {
        Artist a = of("Queen", new LinkedHashSet<>());

        assertThatThrownBy(() -> a.addVariation(duplicate))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("already exists");
        assertThat(a.getVariations()).isEmpty();
    }

    private static Artist of(String title, Set<String> variations) {
        return new Artist(UUID.randomUUID(), title, ArtistType.PERSON, variations);
    }

}
