package com.lumu.qa.sorter;

import com.lumu.qa.model.WordFrequency;

import java.util.List;

public interface FrequencySorter {
    List<WordFrequency> sortDescending(List<WordFrequency> input);
}
