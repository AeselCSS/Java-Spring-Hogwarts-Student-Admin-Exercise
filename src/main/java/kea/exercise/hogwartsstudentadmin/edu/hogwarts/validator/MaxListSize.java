package kea.exercise.hogwartsstudentadmin.edu.hogwarts.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This annotation is used to validate the maximum size of a list.
 * It is used in conjunction with the ListSizeValidator class.
 *
 * @see kea.exercise.hogwartsstudentadmin.edu.hogwarts.validator.ListSizeValidator
 */
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ListSizeValidator.class)
public @interface MaxListSize {
    /**
     * The maximum size of the list.
     *
     * @return the maximum size
     */
    int value();

    /**
     * The default error message if the list exceeds the maximum size.
     *
     * @return the error message
     */
    String message() default "List exceeds maximum size";

    /**
     * The groups the constraint belongs to.
     * This is a more advanced feature of validation constraints that allows for categorization.
     *
     * @return the groups
     */
    Class<?>[] groups() default {};

    /**
     * The payload for the constraint.
     * This is a more advanced feature of validation constraints that allows carrying additional information about the constraint violation.
     *
     * @return the payload
     */
    Class<? extends Payload>[] payload() default {};
}