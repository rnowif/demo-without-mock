package com.zenika.file;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class FileStore {
    private final File rootFolder;

    public FileStore(File rootFolder) {
        this.rootFolder = rootFolder;
    }

    public void store(UserFile file) throws IOException {
        File fileToCreate = new File(rootFolder, file.getPath());

        if (!fileToCreate.getParentFile().exists()) {
            fileToCreate.getParentFile().mkdirs();
        }

        Files.write(Paths.get(fileToCreate.toURI()), file.getContent(), StandardOpenOption.CREATE);
    }
}
