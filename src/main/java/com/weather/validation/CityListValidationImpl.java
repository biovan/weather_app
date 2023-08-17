package com.weather.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

public class CityListValidationImpl implements ConstraintValidator<CityListValidation, List<String>> {

    private List<String> acceptedValues;

    @Override
    public void initialize(CityListValidation constraintAnnotation) {
        var enumClass = constraintAnnotation.enumClass();
        acceptedValues = Arrays.stream(enumClass.getEnumConstants()).map(Enum::name).map(CityList::valueOf).map(CityList::getValue).toList();
    }

    @Override
    public boolean isValid(List<String> stringList, ConstraintValidatorContext constraintValidatorContext) {
        return new HashSet<>(acceptedValues).containsAll(stringList);
    }
}
