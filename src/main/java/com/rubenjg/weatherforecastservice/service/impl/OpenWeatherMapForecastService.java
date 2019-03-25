package com.rubenjg.weatherforecastservice.service.impl;

import com.rubenjg.weatherforecastservice.dto.ForecastDto;
import com.rubenjg.weatherforecastservice.dto.TemperatureDto;
import com.rubenjg.weatherforecastservice.service.ForecastService;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;

@Service
public class OpenWeatherMapForecastService implements ForecastService {

    @Override
    public ForecastDto getForecast(String cityName) {
        ForecastDto test = new ForecastDto(
                OffsetDateTime.now(),
                new TemperatureDto(1.0, 1.0),
                1.0);
        return test;
    }
}
