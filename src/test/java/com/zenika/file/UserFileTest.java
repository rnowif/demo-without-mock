package com.zenika.file;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class UserFileTest {

    @Test
    public void should_compute_path_using_user_name_and_file_name() {

        UserFile file = new UserFile("avatar.png", new byte[]{}, "john");

        assertThat(file.getPath()).isEqualTo("john/files/avatar.png");
    }
}