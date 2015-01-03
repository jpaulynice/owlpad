package com.owlpad.domain.exception;

/**
 * @author Jay Paulynice
 *
 */
public class NoDocFoundException extends RuntimeException {
    private static final long serialVersionUID = 5512528431641900969L;

    /**
     * @param message exception message
     */
    public NoDocFoundException(final String message) {
        super(message);
    }
}
