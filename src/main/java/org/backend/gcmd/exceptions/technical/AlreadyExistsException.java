package org.backend.gcmd.exceptions.technical;

public class AlreadyExistsException extends TechnicalException {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public AlreadyExistsException(String message) {
        super(message);
    }

    public AlreadyExistsException(String message, Throwable cause) {
        super(message, cause);
    }
}