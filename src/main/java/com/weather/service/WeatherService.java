package com.weather.service;

import com.weather.client.GoWeatherClient;
import com.weather.dto.response.WeatherResponse;
import com.weather.mapper.WeatherResponseMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WeatherService {

    private final GoWeatherClient client;
    private final WeatherResponseMapper mapper;

    public Flux<WeatherResponse> get(List<String> cityNameList) {
        return client.get(cityNameList).map(mapper::mapToWeatherResponse);
    }
}
