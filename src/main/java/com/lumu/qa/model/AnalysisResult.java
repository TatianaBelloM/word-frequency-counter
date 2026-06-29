package com.lumu.qa.model;

import java.util.List;

public class AnalysisResult {

    private final int wordCount;
    private final int characterCount;
    private final List<WordFrequency> wordFrequencies;

    private AnalysisResult(Builder builder) {
        this.wordCount = builder.wordCount;
        this.characterCount = builder.characterCount;
        this.wordFrequencies = builder.wordFrequencies;
    }

    public int getWordCount() {
        return wordCount;
    }

    public int getCharacterCount() {
        return characterCount;
    }

    public List<WordFrequency> getWordFrequencies() {
        return wordFrequencies;
    }

    public static class Builder {

        private int wordCount;
        private int characterCount;
        private List<WordFrequency> wordFrequencies;

        public Builder wordCount(int wordCount) {
            this.wordCount = wordCount;
            return this;
        }

        public Builder characterCount(int characterCount) {
            this.characterCount = characterCount;
            return this;
        }

        public Builder wordFrequencies(List<WordFrequency> wordFrequencies) {
            this.wordFrequencies = wordFrequencies;
            return this;
        }

        public AnalysisResult build() {
            return new AnalysisResult(this);
        }
    }
}
