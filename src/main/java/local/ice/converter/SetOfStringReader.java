package local.ice.converter;

import org.springframework.data.convert.ReadingConverter;
import org.springframework.core.convert.converter.Converter;
import org.postgresql.util.PGobject;
import tools.jackson.core.JacksonException;
import tools.jackson.databind.JavaType;
import tools.jackson.databind.ObjectMapper;

import java.util.LinkedHashSet;
import java.util.Set;

@ReadingConverter
public class SetOfStringReader implements Converter<PGobject, Set<String>> {

    private final ObjectMapper mapper;
    private final JavaType outcome;

    public SetOfStringReader(ObjectMapper mapper) {
        this.mapper = mapper;
        this.outcome = mapper.getTypeFactory()
                .constructCollectionType(LinkedHashSet.class, String.class);
    }

    @Override
    public Set<String> convert(PGobject source) {
        try {
            if (source == null || source.getValue() == null || source.getValue().isBlank()) {
                return Set.of();
            }

            return mapper.readValue(source.getValue(), outcome);
        } catch (JacksonException ex) {
            throw new IllegalArgumentException("Could not convert jsonb", ex);
        }
    }
}
