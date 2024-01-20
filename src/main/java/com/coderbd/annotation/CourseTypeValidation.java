package com.coderbd.annotation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = CourseTypeValidator.class)
public @interface CourseTypeValidation {
    String message() default "Course Type one be LIVE OR RECORDED";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
