package edu.eci.arsw.client_spring_rest.exception;

/**
 * Exception thrown when a student is not found in the database.
 * 
 * @author Generated
 * @version 1.0
 */
public class StudentNotFoundException extends RuntimeException {

    public StudentNotFoundException(String message) {
        super(message);
    }

    public StudentNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
