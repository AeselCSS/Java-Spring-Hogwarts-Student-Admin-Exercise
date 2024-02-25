package kea.exercise.hogwartsstudentadmin.edu.hogwarts.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// https://www.bezkoder.com/spring-boot-custom-validation/
/**
 * The @Constraint annotation is used to define a custom constraint annotation.
 * The @Target annotation is used to specify where the annotation can be used.
 * The @Retention annotation is used to specify when the annotation will be available.
 * The @Constraint annotation is used to specify the validator class for the annotation.
 */
@Target({ElementType.FIELD, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ListSizeValidator.class)

public @interface MaxListSize {
    int value();
    String message() default "List exceeds maximum size";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
// NOTE: The groups() and payload() methods are more advanced features of validation constraints.
// It allows categorization constraints and carry additional information about the constraint violation respectively.
// By default, they are set to empty arrays.