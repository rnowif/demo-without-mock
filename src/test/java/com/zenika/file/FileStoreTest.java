package com.zenika.file;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;

public class FileStoreTest {

    @Rule
    public TemporaryFolder temporaryFolder = new TemporaryFolder();

    @Test
    public void should_store_file_in_the_user_folder() throws IOException, URISyntaxException {

        File rootFolder = temporaryFolder.newFolder();
        byte[] content = Files.readAllBytes(Paths.get(ClassLoader.getSystemResource("files/avatar.png").toURI()));

        new FileStore(rootFolder).store(new UserFile("avatar.png", content, "john"));

        File expectedFile = new File(rootFolder, "john/files/avatar.png");
        assertThat(expectedFile).exists().hasBinaryContent(content);
    }
}
