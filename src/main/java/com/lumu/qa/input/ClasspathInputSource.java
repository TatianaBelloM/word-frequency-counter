package com.lumu.qa.input;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class ClasspathInputSource implements TextInputSource {

    private final String resourceName;

    public ClasspathInputSource(String resourceName) {
        this.resourceName = resourceName;
    }

    @Override
    public String read() throws IOException {
        InputStream stream = getClass().getClassLoader().getResourceAsStream(resourceName);
        if (stream == null) {
            throw new IOException("Resource not found on classpath: " + resourceName);
        }
        try (stream) {
            return new String(stream.readAllBytes(), StandardCharsets.UTF_8);
        }
    }
}
