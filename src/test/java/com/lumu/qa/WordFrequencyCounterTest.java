package com.lumu.qa;

import com.lumu.qa.analyzer.WordAnalyzer;
import com.lumu.qa.model.AnalysisResult;
import com.lumu.qa.model.WordFrequency;
import com.lumu.qa.normalizer.AlphaTextNormalizer;
import com.lumu.qa.sorter.FrequencySorter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class WordFrequencyCounterTest {

    private static final String EXAMPLE_TEXT =
            "lumu lumu lumu lumu lumu illuminates illuminates attacks and adversaries\n" +
            "lumu illuminates all attacks and adversaries";

    private WordFrequencyCounter counter;

    @BeforeEach
    void setUp() {
        counter = new WordFrequencyCounter(
                new AlphaTextNormalizer(),
                new WordAnalyzer(),
                new FrequencySorter()
        );
    }

    @Test
    void countsCorrectNumberOfWords() {
        AnalysisResult result = counter.count(EXAMPLE_TEXT);
        assertEquals(16, result.getWordCount());
    }

    @Test
    void countsAllCharactersIncludingNewlines() {
        AnalysisResult result = counter.count(EXAMPLE_TEXT);
        assertEquals(EXAMPLE_TEXT.length(), result.getCharacterCount());
    }

    @Test
    void sortsByFrequencyDescending() {
        AnalysisResult result = counter.count(EXAMPLE_TEXT);
        List<WordFrequency> frequencies = result.getWordFrequencies();
        for (int i = 0; i < frequencies.size() - 1; i++) {
            assertTrue(frequencies.get(i).getCount() >= frequencies.get(i + 1).getCount());
        }
    }

    @Test
    void countsLumuSixTimes() {
        AnalysisResult result = counter.count(EXAMPLE_TEXT);
        assertEquals(6, result.getWordFrequencies().get(0).getCount());
        assertEquals("lumu", result.getWordFrequencies().get(0).getWord());
    }

    @Test
    void countsAndTwiceCorrectingExampleInconsistency() {
        AnalysisResult result = counter.count(EXAMPLE_TEXT);
        WordFrequency andFrequency = result.getWordFrequencies().stream()
                .filter(wf -> wf.getWord().equals("and"))
                .findFirst()
                .orElseThrow();
        assertEquals(2, andFrequency.getCount());
    }

    @Test
    void isCaseInsensitive() {
        AnalysisResult result = counter.count("Java JAVA java");
        assertEquals(3, result.getWordFrequencies().get(0).getCount());
        assertEquals("java", result.getWordFrequencies().get(0).getWord());
    }

    @Test
    void handlesSingleWord() {
        AnalysisResult result = counter.count("lumu");
        assertEquals(1, result.getWordCount());
        assertEquals(4, result.getCharacterCount());
        assertEquals(1, result.getWordFrequencies().get(0).getCount());
    }

    @Test
    void handlesAllUniqueWords() {
        AnalysisResult result = counter.count("one two three");
        assertEquals(3, result.getWordFrequencies().size());
        result.getWordFrequencies().forEach(wf -> assertEquals(1, wf.getCount()));
    }

    @Test
    void handlesSingleWordRepeatedMultipleTimes() {
        AnalysisResult result = counter.count("lumu lumu lumu");
        assertEquals(1, result.getWordFrequencies().size());
        assertEquals(3, result.getWordFrequencies().get(0).getCount());
    }

    @Test
    void countsCharactersOnOriginalTextNotNormalized() {
        AnalysisResult result = counter.count("hello, world.");
        assertEquals(13, result.getCharacterCount());
    }
}
