package com.example.Validation;


import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

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
