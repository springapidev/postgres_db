package com.coderbd.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = PastLocalDateValidator.class)
public @interface PastLocalDate {
    String message() default "Birth Date should be in Past, at least 18 years ago";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
