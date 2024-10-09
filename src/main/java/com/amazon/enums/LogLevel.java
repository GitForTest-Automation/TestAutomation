package com.amazon.enums;

public enum LogLevel {

    LOW(0),
    MEDIUM(1),
    HIGH(2);

    private final int value;

    private LogLevel(final int value) {

        this.value = value;
    }


    public int getValue() {

        return value;
    }
}
