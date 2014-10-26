package com.owlpad.domain.exception;

public class NoConfigFoundException extends RuntimeException {
    private static final long serialVersionUID = -1176063159247892930L;

    public NoConfigFoundException(final String message) {
        super(message);
    }

}
