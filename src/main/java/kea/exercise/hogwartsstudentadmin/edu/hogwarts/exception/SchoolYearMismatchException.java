package kea.exercise.hogwartsstudentadmin.edu.hogwarts.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SchoolYearMismatchException extends RuntimeException{
    private static final Logger logger = LoggerFactory.getLogger(SchoolYearMismatchException.class);
    public SchoolYearMismatchException(String message) {
        super(message);
        logger.info("IllegalArgumentException: " + message);

    }
}
