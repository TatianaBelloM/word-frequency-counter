package com.lumu.qa.analyzer;

import com.lumu.qa.model.WordFrequency;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordAnalyzer implements TextAnalyzer {

    @Override
    public WordAnalysis analyze(String text) {
        String[] words = tokenize(text);
        return new WordAnalysis(words.length, buildFrequencies(words));
    }

    private String[] tokenize(String text) {
        String trimmed = text.trim();
        return trimmed.isEmpty() ? new String[0] : trimmed.split("\\s+");
    }

    private List<WordFrequency> buildFrequencies(String[] words) {
        Map<String, WordFrequency> frequencyMap = new HashMap<>();
        for (String word : words) {
            frequencyMap.computeIfAbsent(word, WordFrequency::new).increment();
        }
        return new ArrayList<>(frequencyMap.values());
    }
}
