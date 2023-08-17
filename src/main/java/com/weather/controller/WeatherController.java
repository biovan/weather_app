package com.weather.controller;

import com.weather.dto.response.WeatherResponse;
import com.weather.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/weather")
public class WeatherController {

    private final WeatherService service;

    @GetMapping
    public ResponseEntity<Flux<WeatherResponse>> get(@RequestParam("city") List<String> cityNameList) {
        return new ResponseEntity<>(service.getForecast(cityNameList), HttpStatus.OK);
    }
}
