package com.velacuss.backend.exceptions;

public class PreconditionFailedException extends RuntimeException {
    private static final String DESCRIPTION = "Precondition Failed (412)";
    public PreconditionFailedException(String detail) {
        super(DESCRIPTION + ". " + detail);
    }
}
