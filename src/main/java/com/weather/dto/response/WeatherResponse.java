package com.weather.dto.response;

import lombok.Data;

@Data
public class WeatherResponse {

    private String name;
    private String temperature;
    private String wind;
}
