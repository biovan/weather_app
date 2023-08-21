package com.weather.dto.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class WeatherResponse {

    private String name;
    private String temperature;
    private String wind;

    public String toCsvLine() {
        return String.format("%s, %s, %s %n", name, temperature, wind);
    }
}
