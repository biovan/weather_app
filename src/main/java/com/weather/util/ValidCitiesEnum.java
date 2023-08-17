package com.weather.util;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

@Getter
public enum ValidCitiesEnum {

    ARAD("Arad"),
    TIMISOARA("Timisoara"),
    CLUJ_NAPOCA("CLuj-Napoca"),
    BUCURESTI("Bucuresti"),
    CONSTANTA("Constanta"),
    BAIA_MARE("Baia-Mare");

    private String value;

    ValidCitiesEnum(String value) {
        this.value = value;
    }

    public static List<String> getValues() {
       return Arrays.stream(ValidCitiesEnum.values()).map(ValidCitiesEnum::getValue).toList();
    }
}
