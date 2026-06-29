package com.lumu.qa.normalizer;

public class AlphaTextNormalizer implements TextNormalizer {

    @Override
    public String normalize(String text) {
        return text.trim().toLowerCase().replaceAll("[^a-z\\s]", "").trim();
    }
}
