package com.lumu.qa.analyzer;

import com.lumu.qa.model.WordFrequency;

import java.util.List;

public record WordAnalysis(int wordCount, List<WordFrequency> frequencies) {}
