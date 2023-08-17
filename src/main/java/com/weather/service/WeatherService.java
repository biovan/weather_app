package com.weather.service;

import com.weather.client.GoWeatherClient;
import com.weather.dto.response.WeatherResponse;
import com.weather.mapper.WeatherResponseMapper;
import com.weather.util.ValidCitiesEnum;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WeatherService {

    private static final List<String> VALID_CITIES_VALUES = ValidCitiesEnum.getValues();

    private final GoWeatherClient goWeatherClient;
    private final WeatherResponseMapper mapper;

    public Flux<WeatherResponse> getForecast(List<String> cityNameList) {
        var validCities = getValidCities(cityNameList);
        return goWeatherClient.get(validCities).map(mapper::mapToWeatherResponse);
    }

    private List<String> getValidCities(List<String> cityList) {
        return cityList.stream().filter(VALID_CITIES_VALUES::contains).toList();
    }
}
