package com.zenika.database;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

public class JdbcUserRepository implements UserRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcUserRepository(DataSource ds) {
        this.jdbcTemplate = new JdbcTemplate(ds);
    }

    @Override
    public void save(User user) {
        jdbcTemplate.update(
                "insert into user (first_name, last_name) values (?, ?)",
                user.getFirstName(), user.getLastName()
        );
    }
}
