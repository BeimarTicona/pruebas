package com.velacuss.backend.exceptions;

public class IAmATeapotException extends RuntimeException {
    private static final String DESCRIPTION = "I'm a teapot Failed (418)";
    public IAmATeapotException(String detail) {super(DESCRIPTION + ". " + detail);}
}
