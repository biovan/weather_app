package com.weather.dto.response.goweather;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class WeatherCityMap {
    private String city;
    private GoWeatherClientResponse weather;
}
