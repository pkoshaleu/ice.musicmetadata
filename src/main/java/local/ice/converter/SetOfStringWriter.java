package local.ice.converter;

import lombok.AllArgsConstructor;
import org.postgresql.util.PGobject;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.convert.WritingConverter;
import tools.jackson.core.JacksonException;
import tools.jackson.databind.ObjectMapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;


@WritingConverter
@AllArgsConstructor
public class SetOfStringWriter implements Converter<Set<String>, PGobject> {

    private final ObjectMapper mapper;

    @Override
    public PGobject convert(Set<String> source) {
        try {
            PGobject jsonObject = new PGobject();
            jsonObject.setType("jsonb");
            jsonObject.setValue(mapper.writeValueAsString(source));
            return jsonObject;
        } catch (JacksonException | SQLException ex) {
            throw new IllegalArgumentException("Could not convert to jsonb", ex);
        }
    }
}
