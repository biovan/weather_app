package com.weather.mapper;

import com.weather.fixtures.Fixtures;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static com.weather.mapper.WeatherResponseMapper.CELSIUS_SIGN;
import static com.weather.mapper.WeatherResponseMapper.KM_PER_HOUR;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class WeatherResponseMapperTest {

    @InjectMocks
    private WeatherResponseMapper mapper;

    @Test
    void mapToWeatherResponse() {
        var city = "Timisoara";
        var forecast = Fixtures.aForecastResponseList();
        var goWeatherResponse = Fixtures.aGoWeatherClientResponse(forecast);
        var cityForecast = Fixtures.aCityForecast(city, goWeatherResponse);

        var expectedTemp = String.format("%s %s", 30, CELSIUS_SIGN);
        var expectedWind = String.format("%s %s", 55, KM_PER_HOUR);

        var weatherResponse = mapper.mapToWeatherResponse(cityForecast);
        assertEquals(city, weatherResponse.getName());
        assertEquals(expectedTemp, weatherResponse.getTemperature());
        assertEquals(expectedWind, weatherResponse.getWind());
    }
}