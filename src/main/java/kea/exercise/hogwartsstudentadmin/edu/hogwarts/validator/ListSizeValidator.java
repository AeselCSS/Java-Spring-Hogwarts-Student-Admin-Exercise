package kea.exercise.hogwartsstudentadmin.edu.hogwarts.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;

/**
 * Class to validate the size of a list
 */
public class ListSizeValidator implements ConstraintValidator<MaxListSize, List<?>> {
    private int max;

    /**
     * Method to initialize the constraint
     * @param constraint the constraint to initialize
     */
    @Override
    public void initialize(MaxListSize constraint) {
        this.max = constraint.value();
    }

    /**
     * Method to validate the constraint
     * @param value the value to validate
     * @param context the context to use
     * @return a boolean
     */
    @Override
    public boolean isValid(List<?> value, ConstraintValidatorContext context) {
        if (value == null) return true;
        return value.size() <= max;
    }
}

