package kea.exercise.hogwartsstudentadmin.edu.hogwarts.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.validation.FieldError;
import java.util.HashMap;
import java.util.Map;

/**
 * This class is a global exception handler that handles all exceptions thrown across the application.
 * It is annotated with @ControllerAdvice, which makes it applicable to all controllers in the application.
 *
 * @see org.springframework.web.bind.annotation.ControllerAdvice
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * This method handles validation exceptions thrown when method arguments are not valid.
     * It is annotated with @ExceptionHandler, which means it is invoked when a MethodArgumentNotValidException is thrown.
     * It returns a map of field names and their corresponding error messages.
     *
     * @param exception the exception that was thrown
     * @return a map of field names and their corresponding error messages
     * @see org.springframework.web.bind.MethodArgumentNotValidException
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException exception) {
        Map<String, String> errors = new HashMap<>();
        exception.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

    /**
     * This method handles exceptions thrown when an entity is not found in the database.
     * It is annotated with @ExceptionHandler, which means it is invoked when an EntityNotFoundException is thrown.
     * It returns a map with a single entry, where the key is "error" and the value is the error message from the exception.
     *
     * @param exception the exception that was thrown
     * @return a map with a single entry, where the key is "error" and the value is the error message from the exception
     * @see EntityNotFoundException
     */
    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public Map<String, String> handleEntityNotFoundExceptions(EntityNotFoundException exception) {
        Map<String, String> errors = new HashMap<>();
        errors.put("error", exception.getMessage());
        return errors;
    }

    /**
     * This method handles exceptions thrown when a resource is not found.
     * It is annotated with @ExceptionHandler, which means it is invoked when a ResourceNotFoundException is thrown.
     * It returns a map with a single entry, where the key is "error" and the value is the error message from the exception.
     *
     * @param exception the exception that was thrown
     * @return a map with a single entry, where the key is "error" and the value is the error message from the exception
     * @see ResourceNotFoundException
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public Map<String, String> handleResourceNotFoundExceptions(ResourceNotFoundException exception) {
        Map<String, String> errors = new HashMap<>();
        errors.put("error", exception.getMessage());
        return errors;
    }

    /**
     * This method handles exceptions thrown when there is a mismatch in the school year.
     * It is annotated with @ExceptionHandler, which means it is invoked when a SchoolYearMismatchException is thrown.
     * It returns a map with a single entry, where the key is "error" and the value is the error message from the exception.
     *
     * @param exception the exception that was thrown
     * @return a map with a single entry, where the key is "error" and the value is the error message from the exception
     * @see SchoolYearMismatchException
     */
    @ExceptionHandler(SchoolYearMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Map<String, String> handleSchoolYearMismatchExceptions(SchoolYearMismatchException exception) {
        Map<String, String> errors = new HashMap<>();
        errors.put("error", exception.getMessage());
        return errors;
    }
}