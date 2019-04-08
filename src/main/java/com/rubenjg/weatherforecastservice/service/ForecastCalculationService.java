package com.rubenjg.weatherforecastservice.service;

import com.rubenjg.weatherforecastservice.dto.ForecastDto;
import com.rubenjg.weatherforecastservice.model.calculation.ForecastContainer;

public interface ForecastCalculationService {

    ForecastDto calculate3DayForecast(ForecastContainer forecastContainer);


}
