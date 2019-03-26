package com.rubenjg.weatherforecastservice.service;

import com.rubenjg.weatherforecastservice.dto.ForecastDto;
import com.rubenjg.weatherforecastservice.exception.ForecastException;

public interface ForecastService {

    ForecastDto get3DayForecast(String cityName) throws ForecastException;
}
