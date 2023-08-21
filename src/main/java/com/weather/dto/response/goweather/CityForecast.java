package com.weather.dto.response.goweather;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CityForecast {
    private String city;
    private GoWeatherClientResponse weather;
}
