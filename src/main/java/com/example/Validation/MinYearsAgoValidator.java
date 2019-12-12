package com.example.Validation;

import lombok.extern.log4j.Log4j2;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.Instant;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

@Log4j2
public class MinYearsAgoValidator implements ConstraintValidator<MinYearsAgo, Date> {

    private int years;
    LocalDate now;

    public void initialize(MinYearsAgo constraint) {
        years = constraint.years();
        now = LocalDate.now();
    }

    public boolean isValid(Date obj, ConstraintValidatorContext context) {
        log.error("Date received from field {}", obj);
        LocalDate target = Instant.ofEpochMilli(obj.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
        Period period = Period.between(target, now);
        return period.getYears() >= years;
    }
}
