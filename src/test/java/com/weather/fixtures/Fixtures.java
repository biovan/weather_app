package com.weather.fixtures;

import com.weather.dto.response.WeatherResponse;
import com.weather.dto.response.goweather.CityForecast;
import com.weather.dto.response.goweather.ForecastResponse;
import com.weather.dto.response.goweather.GoWeatherClientResponse;

import java.util.List;

import static com.weather.mapper.WeatherResponseMapper.CELSIUS_SIGN;
import static com.weather.mapper.WeatherResponseMapper.KM_PER_HOUR;

public class Fixtures {

    public static List<ForecastResponse> aForecastResponseList() {
        var dayOne = ForecastResponse.builder()
                .day("1")
                .temperature(String.format("%s %s", "25", CELSIUS_SIGN))
                .wind(String.format("%s %s", "50", KM_PER_HOUR))
                .build();

        var dayTwo = ForecastResponse.builder()
                .day("2")
                .temperature(String.format("%s %s", "30", CELSIUS_SIGN))
                .wind(String.format("%s %s", "55", KM_PER_HOUR))
                .build();

        var dayThree = ForecastResponse.builder()
                .day("2")
                .temperature(String.format("%s %s", "35", CELSIUS_SIGN))
                .wind(String.format("%s %s", "60", KM_PER_HOUR))
                .build();

        return List.of(dayOne, dayTwo, dayThree);
    }

    public static GoWeatherClientResponse aGoWeatherClientResponse(List<ForecastResponse> forecast) {
        return GoWeatherClientResponse.builder()
                .temperature(String.format("%s %s", "5", CELSIUS_SIGN))
                .wind(String.format("%s %s", "5", KM_PER_HOUR))
                .description("Test mapper")
                .forecast(forecast)
                .build();
    }

    public static CityForecast aCityForecast(String city, GoWeatherClientResponse goWeatherResponse) {
        return CityForecast.builder()
                .city(city)
                .weather(goWeatherResponse)
                .build();
    }

    public static WeatherResponse aWeatherResponse() {
        var response = new WeatherResponse();
        response.setName("Timisoara");
        response.setTemperature("20");
        response.setWind("40");
        return response;
    }

}
