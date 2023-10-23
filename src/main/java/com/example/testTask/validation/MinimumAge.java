package com.example.testTask.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import org.springframework.beans.factory.annotation.Value;

import java.lang.annotation.*;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = MinimumAgeValidator.class)
@Documented
public @interface MinimumAge {

    int value() default 18;
    String message() default "Ваш вік нижче мінімального допустимого віку";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}