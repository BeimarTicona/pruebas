package com.velacuss.backend.exceptions;

public class ErrorMessage {

    private final String error;
    private final String message;
    private final String path;

    public ErrorMessage(Exception exception, String path) {
        String detail = exception.getMessage();
        String[] detalle = detail == null ? new String[0] : detail.split("\\).");
        if (detalle.length > 1) {
            this.error = detalle[0].trim().concat(").");
            this.message = detalle[1].trim();
        } else {
            this.error = exception.getClass().getSimpleName();
            this.message = detalle[0] == null ? null : detalle[0].trim();
        }
        this.path = path;
    }

    public String getError() {
        return error;
    }

    public String getPath() {
        return path;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "ErrorMessage [error=" + error + ", message=" + message + ", path=" + path + "]";
    }

}
