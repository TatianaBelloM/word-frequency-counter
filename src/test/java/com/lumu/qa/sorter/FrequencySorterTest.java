package com.lumu.qa.sorter;

import com.lumu.qa.model.WordFrequency;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FrequencySorterTest {

    private FrequencySorter sorter;

    @BeforeEach
    void setUp() {
        sorter = new FrequencySorter();
    }

    @Test
    void sortsDescendingByCount() {
        List<WordFrequency> input = List.of(
                wordWithCount("a", 1),
                wordWithCount("b", 5),
                wordWithCount("c", 3)
        );
        List<WordFrequency> sorted = sorter.sortDescending(input);
        assertEquals(5, sorted.get(0).getCount());
        assertEquals(3, sorted.get(1).getCount());
        assertEquals(1, sorted.get(2).getCount());
    }

    @Test
    void handlesSingleElement() {
        List<WordFrequency> input = List.of(wordWithCount("only", 4));
        List<WordFrequency> sorted = sorter.sortDescending(input);
        assertEquals(1, sorted.size());
        assertEquals("only", sorted.get(0).getWord());
    }

    @Test
    void handlesAlreadySortedList() {
        List<WordFrequency> input = List.of(
                wordWithCount("x", 10),
                wordWithCount("y", 5),
                wordWithCount("z", 1)
        );
        List<WordFrequency> sorted = sorter.sortDescending(input);
        assertEquals(10, sorted.get(0).getCount());
        assertEquals(1, sorted.get(2).getCount());
    }

    @Test
    void handlesTiedFrequencies() {
        List<WordFrequency> input = List.of(
                wordWithCount("a", 2),
                wordWithCount("b", 2),
                wordWithCount("c", 2)
        );
        List<WordFrequency> sorted = sorter.sortDescending(input);
        sorted.forEach(wf -> assertEquals(2, wf.getCount()));
    }

    @Test
    void doesNotMutateInputList() {
        WordFrequency first = wordWithCount("low", 1);
        WordFrequency second = wordWithCount("high", 9);
        List<WordFrequency> input = List.of(first, second);
        sorter.sortDescending(input);
        assertEquals("low", input.get(0).getWord());
    }

    @Test
    void handlesEmptyList() {
        List<WordFrequency> sorted = sorter.sortDescending(List.of());
        assertTrue(sorted.isEmpty());
    }

    @Test
    void handlesReverseSortedInput() {
        List<WordFrequency> input = List.of(
                wordWithCount("a", 1),
                wordWithCount("b", 2),
                wordWithCount("c", 3)
        );
        List<WordFrequency> sorted = sorter.sortDescending(input);
        assertEquals(3, sorted.get(0).getCount());
        assertEquals(2, sorted.get(1).getCount());
        assertEquals(1, sorted.get(2).getCount());
    }

    private WordFrequency wordWithCount(String word, int count) {
        WordFrequency wf = new WordFrequency(word);
        for (int i = 0; i < count; i++) {
            wf.increment();
        }
        return wf;
    }
}
