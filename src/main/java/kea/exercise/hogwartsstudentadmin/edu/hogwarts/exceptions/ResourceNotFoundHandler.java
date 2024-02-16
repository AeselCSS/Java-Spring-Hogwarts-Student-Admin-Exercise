package kea.exercise.hogwartsstudentadmin.edu.hogwarts.exceptions;

public class ResourceNotFoundHandler extends RuntimeException {
    public ResourceNotFoundHandler(String message) {
        super(message);
    }
}
