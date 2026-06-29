package com.lumu.qa.input;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileInputSource implements TextInputSource {

    private final String filePath;

    public FileInputSource(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public String read() throws IOException {
        return Files.readString(Path.of(filePath));
    }
}
