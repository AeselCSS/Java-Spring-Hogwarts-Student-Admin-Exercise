package kea.exercise.hogwartsstudentadmin.edu.hogwarts.validator;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// https://www.bezkoder.com/spring-boot-custom-validation/
@Target({ElementType.FIELD, ElementType.METHOD}) // NOTE: This annotation can only be used on fields and methods
@Retention(RetentionPolicy.RUNTIME) // NOTE: This annotation will be available at runtime
@Constraint(validatedBy = ListSizeValidator.class) // NOTE: This annotation will be validated by ListSizeValidator
public @interface MaxListSize {
    int value();
    String message() default "List exceeds maximum size";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
// NOTE: The groups() and payload() methods are more advanced features of validation constraints.
// It allows categorization constraints and carry additional information about the constraint violation respectively.
// By default, they are set to empty arrays.