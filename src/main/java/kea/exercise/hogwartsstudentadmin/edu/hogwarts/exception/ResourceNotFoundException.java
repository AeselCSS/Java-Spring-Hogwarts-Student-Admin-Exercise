package kea.exercise.hogwartsstudentadmin.edu.hogwarts.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
