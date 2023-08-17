package com.weather.validation;

import lombok.Getter;

@Getter
public enum CityList {

    ARAD("Arad"),
    TIMISOARA("Timisoara"),
    CLUJ_NAPOCA("CLuj-Napoca"),
    BUCURESTI("Bucuresti"),
    CRAIOVA("Craiova"),
    DEJ("Dej"),
    CONSTANTA("Constanta"),
    BAIA_MARE("Baia-Mare"),
    BISTRITA("Bistrita"),
    IASI("Iasi"),
    ORADEA("Oradea");

    private String value;

    CityList(String value) {
        this.value = value;
    }
}
