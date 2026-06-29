package com.lumu.qa.analyzer;

import com.lumu.qa.model.WordFrequency;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class WordAnalyzerTest {

    private WordAnalyzer analyzer;

    @BeforeEach
    void setUp() {
        analyzer = new WordAnalyzer();
    }

    @Test
    void countsWordsCorrectly() {
        assertEquals(3, analyzer.analyze("one two three").wordCount());
    }

    @Test
    void treatsMultipleSpacesAsSingleSeparator() {
        assertEquals(2, analyzer.analyze("one   two").wordCount());
    }

    @Test
    void treatTabsAsSeparator() {
        assertEquals(3, analyzer.analyze("one\ttwo\tthree").wordCount());
    }

    @Test
    void ignoresLeadingAndTrailingWhitespace() {
        assertEquals(2, analyzer.analyze("  hello world  ").wordCount());
    }

    @Test
    void buildsFrequencyMapCorrectly() {
        WordAnalysis result = analyzer.analyze("a b a c a b");
        assertEquals(3, result.frequencies().size());
        long countA = result.frequencies().stream()
                .filter(wf -> wf.getWord().equals("a"))
                .findFirst().orElseThrow().getCount();
        assertEquals(3, countA);
    }

    @Test
    void handlesEmptyText() {
        WordAnalysis result = analyzer.analyze("");
        assertEquals(0, result.wordCount());
        assertTrue(result.frequencies().isEmpty());
    }

    @Test
    void handlesSingleWord() {
        WordAnalysis result = analyzer.analyze("lumu");
        assertEquals(1, result.wordCount());
        assertEquals(1, result.frequencies().get(0).getCount());
    }
}
