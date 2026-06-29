package com.lumu.qa;

import com.lumu.qa.analyzer.WordAnalyzer;
import com.lumu.qa.input.ClasspathInputSource;
import com.lumu.qa.input.FileInputSource;
import com.lumu.qa.input.TextInputSource;
import com.lumu.qa.model.AnalysisResult;
import com.lumu.qa.model.WordFrequency;
import com.lumu.qa.normalizer.AlphaTextNormalizer;
import com.lumu.qa.sorter.MergeFrequencySorter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Files;
import java.nio.file.Path;

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
                new MergeFrequencySorter()
        );

        try {
            String text = resolveInput(args[0]).read();
            AnalysisResult result = counter.count(text);
            printResult(result);
        } catch (Exception e) {
            logger.error("Error reading file: {}", e.getMessage());
            System.exit(1);
        }
    }

    private static TextInputSource resolveInput(String arg) {
        if (Files.exists(Path.of(arg))) {
            return new FileInputSource(arg);
        }
        return new ClasspathInputSource(arg);
    }

    private static void printResult(AnalysisResult result) {
        System.out.println(result.getWordCount() + " words");
        System.out.println(result.getCharacterCount() + " characters");
        for (WordFrequency wf : result.getWordFrequencies()) {
            System.out.println(wf.getWord() + ": " + wf.getCount());
        }
    }
}
