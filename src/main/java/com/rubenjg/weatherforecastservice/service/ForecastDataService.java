package com.rubenjg.weatherforecastservice.service;

import com.rubenjg.weatherforecastservice.model.calculation.ForecastContainer;

public interface ForecastDataService {

    String getName();

    ForecastContainer get3DayForecast(String cityName);
}
