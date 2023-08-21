package com.weather.client;

import com.weather.dto.response.goweather.GoWeatherClientResponse;
import com.weather.dto.response.goweather.CityForecast;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class GoWeatherClient {

    @Value("${goweather.url}")
    private String url;

    public Flux<CityForecast> get(List<String> cityNameList) {
        return Flux.fromIterable(cityNameList)
                .flatMap(this::getMono)
                .sort(Comparator.comparing(CityForecast::getCity));
    }

    private Mono<CityForecast> getMono(String cityName) {
        return getWebClient()
                .get()
                .uri("{city_name}", cityName)
                .retrieve()
                .bodyToMono(GoWeatherClientResponse.class)
                .map(goWeatherClientResponse -> new CityForecast(cityName, goWeatherClientResponse));
    }

    private WebClient getWebClient() {
        return WebClient.builder()
                .baseUrl(url)
                .build();
    }
}
