package com.weather.dto.response.goweather;

import lombok.Data;

import java.util.List;

@Data
public class GoWeatherClientResponse {

    private String temperature;
    private String wind;
    private String description;
    private List<ForecastResponse> forecast;
}
