package com.lumu.qa.input;

public class PlainTextInputSource implements TextInputSource {

    private final String text;

    public PlainTextInputSource(String text) {
        this.text = text;
    }

    @Override
    public String read() {
        return text;
    }
}
