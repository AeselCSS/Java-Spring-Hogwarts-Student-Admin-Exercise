package kea.exercise.hogwartsstudentadmin.edu.hogwarts.exception;

/**
 * Custom exception for when an school year mismatch occurs
 */
public class SchoolYearMismatchException extends RuntimeException{
    /**
     * Constructor
     * @param message the message
     */
    public SchoolYearMismatchException(String message) {
        super(message);

    }
}
