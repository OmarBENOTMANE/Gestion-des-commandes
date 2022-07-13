package org.backend.gcmd.exceptions.business;

public class ConstraintViolationException extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public ConstraintViolationException(String message) {
        super(message);
    }

    public ConstraintViolationException(String message, Throwable cause) {
        super(message, cause);
    }

}
