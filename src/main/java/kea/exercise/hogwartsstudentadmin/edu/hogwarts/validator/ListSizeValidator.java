package kea.exercise.hogwartsstudentadmin.edu.hogwarts.validator;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.List;

public class ListSizeValidator implements ConstraintValidator<MaxListSize, List<?>> {
    private int max;

    @Override
    public void initialize(MaxListSize constraint) {
        this.max = constraint.value();
    }

    @Override
    public boolean isValid(List<?> value, ConstraintValidatorContext context) {
        if (value == null) return true;
        return value.size() <= max;
    }
}

