package com.unnathy.fieldwise.common.domain;

public class TaomishError extends Exception {
    public TaomishError(String message) {
        super(message);
    }

    public TaomishError(String message, Throwable cause) {
        super(message, cause);
    }
}
