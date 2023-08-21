package com.weather.dto.response.goweather;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ForecastResponse {
    private String day;
    private String temperature;
    private String wind;
}
