package com.zenika.database;

import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

import static org.assertj.core.api.Assertions.assertThat;

public class JdbcUserRepositoryTest {

    @Test
    public void should_save_user_to_database() {
        DataSource ds = dataSource();
        JdbcUserRepository userRepository = new JdbcUserRepository(ds);

        userRepository.save(new User("John", "Doe"));

        assertThat(count(ds, "John", "Doe")).isEqualTo(1);
    }

    private int count(DataSource ds, final String firstName, final String lastName) {
        return new JdbcTemplate(ds).queryForObject(
                "select count(*) from user where first_name = ? and last_name = ?",
                Integer.class,
                firstName, lastName
        );
    }

    private DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.HSQL)
                .addScript("db/sql/create-db.sql")
                .build();
    }
}
