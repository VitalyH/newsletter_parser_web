package com.vitaly.h.newsletterparserweb;

public class ParserModel {
    private String originalText;


    public ParserModel() {
        super();
    }

    ParserModel(String originalText) {
        super();
        this.originalText = originalText;

    }

    String getOriginalText() {
        return originalText;
    }

    public void setOriginalText(String originalText) {
        this.originalText = originalText;
    }
}


