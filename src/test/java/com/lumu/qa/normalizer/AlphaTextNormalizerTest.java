package com.lumu.qa.normalizer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class AlphaTextNormalizerTest {

    private AlphaTextNormalizer normalizer;

    @BeforeEach
    void setUp() {
        normalizer = new AlphaTextNormalizer();
    }

    @Test
    void convertsToLowercase() {
        assertEquals("hello world", normalizer.normalize("Hello WORLD"));
    }

    @Test
    void removesPunctuation() {
        assertEquals("hello world", normalizer.normalize("hello, world."));
    }

    @Test
    void removesSpecialCharactersLeavingWhitespace() {
        assertEquals("hello  world", normalizer.normalize("hello --- world"));
    }

    @Test
    void stripsLeadingAndTrailingWhitespace() {
        assertEquals("hello", normalizer.normalize("  hello  "));
    }

    @Test
    void treatsWordWithAndWithoutPunctuationAsSameToken() {
        assertEquals("lumu lumu lumu", normalizer.normalize("lumu lumu. lumu,"));
    }

    @Test
    void handlesEmptyString() {
        assertTrue(normalizer.normalize("").isEmpty());
    }

    @Test
    void handlesOnlyPunctuation() {
        assertTrue(normalizer.normalize("--- ...").isEmpty());
    }

    @Test
    void preservesWhitespaceBetweenWords() {
        String result = normalizer.normalize("one two");
        assertEquals("one two", result);
    }
}
