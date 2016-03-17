package com.zenika;

import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

import static org.assertj.core.api.Assertions.assertThat;

public class UserRepositoryTest {

    private DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.HSQL)
                .addScript("db/sql/create-db.sql")
                .build();
    }

    @Test
    public void should_save_user_to_database() {
        DataSource ds = dataSource();
        UserRepository userRepository = new UserRepository(ds);

        userRepository.save(new User("John", "Doe"));

        JdbcTemplate jdbcTemplate = new JdbcTemplate(ds);
        int count = jdbcTemplate.queryForObject("select count(*) from user where first_name = 'John' and last_name = 'Doe'", Integer.class);
        assertThat(count).isEqualTo(1);
    }
}
