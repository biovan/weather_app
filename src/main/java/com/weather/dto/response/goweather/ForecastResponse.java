package com.weather.dto.response.goweather;

import lombok.Data;

@Data
public class ForecastResponse {
    private String day;
    private String temperature;
    private String wind;
}
