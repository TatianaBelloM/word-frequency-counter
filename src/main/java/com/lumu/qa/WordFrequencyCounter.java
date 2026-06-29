package com.lumu.qa;

import com.lumu.qa.analyzer.TextAnalyzer;
import com.lumu.qa.analyzer.WordAnalysis;
import com.lumu.qa.model.AnalysisResult;
import com.lumu.qa.normalizer.TextNormalizer;
import com.lumu.qa.sorter.FrequencySorter;

public class WordFrequencyCounter {

    private final TextNormalizer normalizer;
    private final TextAnalyzer analyzer;
    private final FrequencySorter sorter;

    public WordFrequencyCounter(TextNormalizer normalizer, TextAnalyzer analyzer, FrequencySorter sorter) {
        this.normalizer = normalizer;
        this.analyzer = analyzer;
        this.sorter = sorter;
    }

    public AnalysisResult count(String text) {
        String normalized = normalizer.normalize(text);
        WordAnalysis analysis = analyzer.analyze(normalized);
        return new AnalysisResult.Builder()
                .characterCount(text.length())
                .wordCount(analysis.wordCount())
                .wordFrequencies(sorter.sortDescending(analysis.frequencies()))
                .build();
    }
}
