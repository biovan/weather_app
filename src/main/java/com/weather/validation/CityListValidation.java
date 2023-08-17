package com.weather.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Target( {ElementType.FIELD, ElementType.PARAMETER, ElementType.METHOD, ElementType.LOCAL_VARIABLE, ElementType.TYPE_PARAMETER, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CityListValidationImpl.class)
public @interface CityListValidation {

    Class<? extends Enum<?>> enumClass();

    String message() default "List contains invalid city";

    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
