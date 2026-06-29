package com.lumu.qa.input;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TextInputSourceTest {

    @Test
    void plainTextInputSourceReturnsTextAsIs() throws IOException {
        String text = "lumu illuminates attacks";
        TextInputSource source = new PlainTextInputSource(text);
        assertEquals(text, source.read());
    }

    @Test
    void fileInputSourceReadsFileContent(@TempDir Path tempDir) throws IOException {
        String content = "lumu illuminates attacks";
        Path file = tempDir.resolve("test.txt");
        Files.writeString(file, content);

        TextInputSource source = new FileInputSource(file.toString());
        assertEquals(content, source.read());
    }

    @Test
    void bothSourcesReturnSameTextForSameContent(@TempDir Path tempDir) throws IOException {
        String content = "lumu lumu illuminates";
        Path file = tempDir.resolve("test.txt");
        Files.writeString(file, content);

        TextInputSource fileSource = new FileInputSource(file.toString());
        TextInputSource plainSource = new PlainTextInputSource(content);

        assertEquals(plainSource.read(), fileSource.read());
    }

    @Test
    void fileInputSourceThrowsIOExceptionForNonExistentFile() {
        TextInputSource source = new FileInputSource("non_existent_file.txt");
        assertThrows(IOException.class, source::read);
    }

    @Test
    void fileInputSourceReadsEmptyFile(@TempDir Path tempDir) throws IOException {
        Path file = tempDir.resolve("empty.txt");
        Files.writeString(file, "");

        TextInputSource source = new FileInputSource(file.toString());
        assertEquals("", source.read());
    }

    @Test
    void plainTextInputSourceReturnsEmptyString() throws IOException {
        TextInputSource source = new PlainTextInputSource("");
        assertEquals("", source.read());
    }

    @Test
    void fileInputSourceReadsRealLoremIpsumFile() throws IOException, URISyntaxException {
        Path filePath = Path.of(getClass().getClassLoader().getResource("lorem-ipsum.txt").toURI());
        TextInputSource source = new FileInputSource(filePath.toString());

        String content = source.read();

        assertFalse(content.isBlank());
        assertTrue(content.contains("lorem"));
        assertTrue(content.contains("ipsum"));
    }
}
