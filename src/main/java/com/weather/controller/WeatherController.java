package com.weather.controller;

import com.weather.dto.response.WeatherResponse;
import com.weather.service.WeatherService;
import com.weather.validation.CityList;
import com.weather.validation.CityListValidation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.util.List;

@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("/api/weather")
public class WeatherController {

    private final WeatherService service;

    @GetMapping
    public ResponseEntity<Flux<WeatherResponse>> get(
            @Valid
            @RequestParam("city")
            @CityListValidation(enumClass = CityList.class)
            List<String> cityNameList) {
        return new ResponseEntity<>(service.get(cityNameList), HttpStatus.OK);
    }
}
