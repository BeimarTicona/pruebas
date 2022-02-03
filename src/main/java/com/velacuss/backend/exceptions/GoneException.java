package com.velacuss.backend.exceptions;

public class GoneException extends RuntimeException {
    private static final String DESCRIPTION = "Gone Exception (410)";
    public GoneException(String detail) {
        super(DESCRIPTION + ". " + detail);
    }
}
