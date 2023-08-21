package com.weather.dto.response.goweather;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class GoWeatherClientResponse {

    private String temperature;
    private String wind;
    private String description;
    private List<ForecastResponse> forecast;
}
