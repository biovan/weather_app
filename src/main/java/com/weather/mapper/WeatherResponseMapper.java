package com.weather.mapper;

import com.weather.dto.response.WeatherResponse;
import com.weather.dto.response.goweather.ForecastResponse;
import com.weather.dto.response.goweather.WeatherCityMap;
import io.micrometer.common.util.StringUtils;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.List;

@Service
public class WeatherResponseMapper {

    public WeatherResponse mapToWeatherResponse(WeatherCityMap source) {
        var response = new WeatherResponse();
        response.setName(source.getCity());
        response.setTemperature(String.format("%s °C", getTemperatureMean(source.getWeather().getForecast())));
        response.setWind(String.format("%s km/h", getWindMean(source.getWeather().getForecast())));
        return response;
    }

    private String getTemperatureMean(List<ForecastResponse> forecastList) {
        var decimalFormat = new DecimalFormat("0.##");
        var days = 0;
        double mean = Double.parseDouble("0");
        for (ForecastResponse f : forecastList) {
            var stringValue = f.getTemperature().replace("°C", "");
            if (!StringUtils.isBlank(stringValue)) {
                mean += Double.parseDouble(stringValue);
                days++;
            }
        }
        return decimalFormat.format(mean / days);
    }

    private String getWindMean(List<ForecastResponse> forecastList) {
        var decimalFormat = new DecimalFormat("0.##");
        double mean = Double.parseDouble("0");
        for (ForecastResponse f : forecastList) {
            mean += Double.parseDouble(f.getWind().replace("km/h", ""));
        }
        return decimalFormat.format(mean / 3);
    }

}
