package com.velacuss.backend.exceptions;

public class LockedException extends RuntimeException {
    private static final String DESCRIPTION = "Locked Exception (423)";
    public LockedException(String detail) {
        super(DESCRIPTION + ". " + detail);
    }
}
