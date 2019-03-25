package com.rubenjg.weatherforecastservice.service;

import com.rubenjg.weatherforecastservice.dto.ForecastDto;

public interface ForecastService {

    ForecastDto getForecast(String cityName);
}
