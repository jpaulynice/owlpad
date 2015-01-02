package com.owlpad.domain.exception;

/**
 * @author Jay Paulynice
 *
 */
public class NoConfigFoundException extends RuntimeException {
    private static final long serialVersionUID = -1176063159247892930L;

    /**
     * @param message
     *            exception message
     */
    public NoConfigFoundException(final String message) {
        super(message);
    }

}
