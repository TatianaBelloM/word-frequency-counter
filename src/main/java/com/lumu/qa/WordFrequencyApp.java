package com.lumu.qa;

import com.lumu.qa.analyzer.WordAnalyzer;
import com.lumu.qa.input.FileInputSource;
import com.lumu.qa.model.AnalysisResult;
import com.lumu.qa.model.WordFrequency;
import com.lumu.qa.normalizer.AlphaTextNormalizer;
import com.lumu.qa.sorter.FrequencySorter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WordFrequencyApp {

    private static final Logger logger = LoggerFactory.getLogger(WordFrequencyApp.class);

    public static void main(String[] args) {
        if (args.length == 0) {
            logger.error("Usage: WordFrequencyApp <file-path>");
            System.exit(1);
        }

        WordFrequencyCounter counter = new WordFrequencyCounter(
                new AlphaTextNormalizer(),
                new WordAnalyzer(),
                new FrequencySorter()
        );

        try {
            String text = new FileInputSource(args[0]).read();
            AnalysisResult result = counter.count(text);
            printResult(result);
        } catch (Exception e) {
            logger.error("Error reading file: {}", e.getMessage());
            System.exit(1);
        }
    }

    private static void printResult(AnalysisResult result) {
        System.out.println(result.getWordCount() + " words");
        System.out.println(result.getCharacterCount() + " characters");
        for (WordFrequency wf : result.getWordFrequencies()) {
            System.out.println(wf.getWord() + ": " + wf.getCount());
        }
    }
}
