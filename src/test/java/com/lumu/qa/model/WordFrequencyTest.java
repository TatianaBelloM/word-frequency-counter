package com.lumu.qa.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WordFrequencyTest {

    @Test
    void startsWithCountZero() {
        WordFrequency wf = new WordFrequency("lumu");
        assertEquals(0, wf.getCount());
    }

    @Test
    void incrementIncreasesCountByOne() {
        WordFrequency wf = new WordFrequency("lumu");
        wf.increment();
        assertEquals(1, wf.getCount());
    }

    @Test
    void multipleIncrementsAccumulate() {
        WordFrequency wf = new WordFrequency("lumu");
        wf.increment();
        wf.increment();
        wf.increment();
        assertEquals(3, wf.getCount());
    }

    @Test
    void getWordReturnsOriginalWord() {
        WordFrequency wf = new WordFrequency("illuminates");
        assertEquals("illuminates", wf.getWord());
    }
}
