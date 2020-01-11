package com.example.Validation;


import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Constraint(validatedBy = MinYearsAgoValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Documented
public @interface MinYearsAgo {
    String message() default "{com.example.Validation.MinYearsAgoValidator.message}";

    int years();

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
