package kea.exercise.hogwartsstudentadmin.edu.hogwarts.exception;

/**
 * Custom exception for when a resource is not found
 */
public class ResourceNotFoundException extends RuntimeException {
    /**
     * Constructor
     * @param message the message
     */
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
