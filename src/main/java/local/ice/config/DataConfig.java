package local.ice.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jdbc.core.convert.JdbcCustomConversions;
import tools.jackson.databind.ObjectMapper;

import local.ice.converter.SetOfStringReader;
import local.ice.converter.SetOfStringWriter;

import java.util.List;

@Configuration
public class DataConfig {

    @Bean
    public JdbcCustomConversions jdbcCustomConversions(ObjectMapper objectMapper) {
        return new JdbcCustomConversions(
            List.of(
                new SetOfStringReader(objectMapper),
                new SetOfStringWriter(objectMapper)
            )
        );
    }

}
