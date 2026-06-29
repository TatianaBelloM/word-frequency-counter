package com.lumu.qa.sorter;

import com.lumu.qa.model.WordFrequency;

import java.util.ArrayList;
import java.util.List;

public class FrequencySorter {

    public List<WordFrequency> sortDescending(List<WordFrequency> input) {
        if (input.size() <= 1) {
            return new ArrayList<>(input);
        }
        int mid = input.size() / 2;
        List<WordFrequency> left = sortDescending(input.subList(0, mid));
        List<WordFrequency> right = sortDescending(input.subList(mid, input.size()));
        return merge(left, right);
    }

    private List<WordFrequency> merge(List<WordFrequency> left, List<WordFrequency> right) {
        List<WordFrequency> merged = new ArrayList<>(left.size() + right.size());
        int i = 0;
        int j = 0;
        while (i < left.size() && j < right.size()) {
            if (left.get(i).getCount() >= right.get(j).getCount()) {
                merged.add(left.get(i));
                i++;
            } else {
                merged.add(right.get(j));
                j++;
            }
        }
        merged.addAll(left.subList(i, left.size()));
        merged.addAll(right.subList(j, right.size()));
        return merged;
    }
}
