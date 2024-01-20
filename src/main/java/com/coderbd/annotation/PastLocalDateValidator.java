package com.coderbd.annotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;

public class PastLocalDateValidator implements ConstraintValidator<PastLocalDate, LocalDate> {
    @Override
    public boolean isValid(LocalDate localDate, ConstraintValidatorContext constraintValidatorContext) {
        // If the date is null, it's considered valid to allow optional fields
        return localDate == null || localDate.isBefore(LocalDate.now().minusYears(18));
    }
}
