package com.velacuss.backend.exceptions;

public class InternalServerErrorException extends RuntimeException  {
    private static final String DESCRIPTION = "Internal Server Error (500)";
    public InternalServerErrorException(String detail) {
        super(DESCRIPTION + ". " + detail);
    }
}
