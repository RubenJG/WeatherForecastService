package com.rubenjg.weatherforecastservice.service;

import com.rubenjg.weatherforecastservice.dto.ForecastDto;

public interface ForecastService {

    ForecastDto get3DayForecast(String cityName);
}
