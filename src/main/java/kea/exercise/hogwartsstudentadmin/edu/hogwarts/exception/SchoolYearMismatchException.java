package kea.exercise.hogwartsstudentadmin.edu.hogwarts.exception;

/**
 * This class is a custom exception that is thrown when there is a mismatch in the school year.
 * It extends the RuntimeException class, which means it is an unchecked exception.
 * Unchecked exceptions are exceptions that can be thrown during the execution of the program but are not checked at compile time.
 * The class contains a constructor that takes a message as a parameter and passes it to the superclass constructor.
 *
 * @see java.lang.RuntimeException
 */
public class SchoolYearMismatchException extends RuntimeException{

    /**
     * This constructor initializes a new instance of the SchoolYearMismatchException class.
     * It takes a message as a parameter, which describes the details of the error, and passes it to the superclass constructor.
     *
     * @param message the detail message, saved for later retrieval by the Throwable.getMessage() method
     */
    public SchoolYearMismatchException(String message) {
        super(message);
    }
}