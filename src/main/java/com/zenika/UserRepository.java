package com.zenika;

import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

public class UserRepository {

    private final JdbcTemplate jdbcTemplate;

    public UserRepository(DataSource ds) {
        this.jdbcTemplate = new JdbcTemplate(ds);
    }

    public void save(User user) {
        jdbcTemplate.update(
                "insert into user (first_name, last_name) values (?, ?)",
                user.getFirstName(), user.getLastName()
        );
    }
}
