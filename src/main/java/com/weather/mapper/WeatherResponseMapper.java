package com.weather.mapper;

import com.weather.dto.response.WeatherResponse;
import com.weather.dto.response.goweather.ForecastResponse;
import com.weather.dto.response.goweather.CityForecast;
import io.micrometer.common.util.StringUtils;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.List;

@Service
public class WeatherResponseMapper {

    public static final String CELSIUS_SIGN = "°C";
    public static final String KM_PER_HOUR = "km/h";

    public WeatherResponse mapToWeatherResponse(CityForecast source) {
        var response = new WeatherResponse();
        response.setName(source.getCity());
        response.setTemperature(String.format("%s %s", getTemperatureMean(source.getWeather().getForecast()), CELSIUS_SIGN));
        response.setWind(String.format("%s %s", getWindMean(source.getWeather().getForecast()), KM_PER_HOUR));
        return response;
    }

    private String getTemperatureMean(List<ForecastResponse> forecastList) {
        var decimalFormat = new DecimalFormat("0.##");
        var days = 0;
        double mean = Double.parseDouble("0");
        for (ForecastResponse f : forecastList) {
            var stringValue = f.getTemperature().replace(CELSIUS_SIGN, "");
            if (!StringUtils.isBlank(stringValue)) {
                mean += Double.parseDouble(stringValue);
                days++;
            }
        }
        return decimalFormat.format(mean / (days > 0 ? days : 1));
    }

    private String getWindMean(List<ForecastResponse> forecastList) {
        var decimalFormat = new DecimalFormat("0.##");
        double mean = Double.parseDouble("0");
        for (ForecastResponse f : forecastList) {
            mean += Double.parseDouble(f.getWind().replace(KM_PER_HOUR, ""));
        }
        return decimalFormat.format(mean / 3);
    }

}
