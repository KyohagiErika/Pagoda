package com.processor.enumeration;

/**
 * Console input mode.
 */
public enum ConsoleInputMode {
    TEXT("text"),
    PASSWORD("password");

    private String modeValue;

    public String getValue() {
        return modeValue;
    }

    ConsoleInputMode(String modeValue) {
        this.modeValue = modeValue;
    }
}
