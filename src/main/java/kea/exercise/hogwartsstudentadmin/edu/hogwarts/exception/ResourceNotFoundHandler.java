package kea.exercise.hogwartsstudentadmin.edu.hogwarts.exception;

public class ResourceNotFoundHandler extends RuntimeException {
    public ResourceNotFoundHandler(String message) {
        super(message);
    }
}
