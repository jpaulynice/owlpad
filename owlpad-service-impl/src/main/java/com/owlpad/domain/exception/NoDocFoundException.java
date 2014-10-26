package com.owlpad.domain.exception;

public class NoDocFoundException extends RuntimeException {
    private static final long serialVersionUID = 5512528431641900969L;

    public NoDocFoundException(final String message) {
        super(message);
    }

}
