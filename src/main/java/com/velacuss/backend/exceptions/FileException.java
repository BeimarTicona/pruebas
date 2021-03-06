package com.velacuss.backend.exceptions;

public class FileException extends NotFoundException {
    private static final String DESCRIPTION = "File exception";

    public FileException(String detail) {
        super(DESCRIPTION + ". " + detail);
    }

}
