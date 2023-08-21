package com.weather.controller;

import com.weather.fixtures.Fixtures;
import com.weather.service.WeatherService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.client.WebClientException;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.Charset;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@WebFluxTest(WeatherController.class)
class WeatherControllerTest {

    @Autowired
    private WebTestClient webClient;

    @MockBean
    private WeatherService weatherService;

    @Test
    void getWeather() {
        var responseOne = Fixtures.aWeatherResponse();
        var responseTwo = Fixtures.aWeatherResponse();

        when(weatherService.getForecast(any())).thenReturn(Flux.just(responseOne, responseTwo));

        webClient
                .get()
                .uri("/api/weather?city=Arad,Timisoara")
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody(List.class);
    }

    @Test
    void getWeather_throws5xxException_whenClientError() {
        byte[] dummyBody = new byte[1];
        when(weatherService.getForecast(any()))
                .thenReturn(Flux.error(new WebClientResponseException(404, "Test Mock", HttpHeaders.EMPTY, dummyBody, Charset.defaultCharset())));

        webClient
                .get()
                .uri("/api/weather?city=Arad,Timisoara")
                .exchange()
                .expectStatus()
                .is5xxServerError()
                .expectBody(String.class);
    }
}