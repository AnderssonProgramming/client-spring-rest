package edu.eci.arsw.client_spring_rest.exception;

/**
 * Exception thrown when attempting to create a student with an email that already exists.
 * 
 * @author Generated
 * @version 1.0
 */
public class DuplicateEmailException extends RuntimeException {

    public DuplicateEmailException(String message) {
        super(message);
    }

    public DuplicateEmailException(String message, Throwable cause) {
        super(message, cause);
    }
}
