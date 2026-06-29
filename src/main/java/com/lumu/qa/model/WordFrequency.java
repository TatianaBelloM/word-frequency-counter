package com.lumu.qa.model;

public class WordFrequency {

    private final String word;
    private int count;

    public WordFrequency(String word) {
        this.word = word;
        this.count = 0;
    }

    public String getWord() {
        return word;
    }

    public int getCount() {
        return count;
    }

    public void increment() {
        count++;
    }
}
