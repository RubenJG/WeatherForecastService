package com.rubenjg.weatherforecastservice.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rubenjg.weatherforecastservice.client.OpenWeatherMapFeignClient;
import com.rubenjg.weatherforecastservice.model.calculation.Data;
import com.rubenjg.weatherforecastservice.model.calculation.ForecastContainer;
import com.rubenjg.weatherforecastservice.model.openweathermap.Forecast;
import com.rubenjg.weatherforecastservice.model.openweathermap.Response;
import com.rubenjg.weatherforecastservice.service.ForecastDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class OpenWeatherMapDataForecastService implements ForecastDataService {

    private Logger logger = LoggerFactory.getLogger(OpenWeatherMapDataForecastService.class);

    private final OpenWeatherMapFeignClient openWeatherMapFeignClient;
    private final String applicationKey;

    @Autowired
    public OpenWeatherMapDataForecastService(
            ObjectMapper objectMapper,
            OpenWeatherMapFeignClient openWeatherMapFeignClient,
            @Value("${service.open-weather-map.api-key}") String applicationKey) {
        this.openWeatherMapFeignClient = openWeatherMapFeignClient;
        this.applicationKey = applicationKey;
    }

    @Override
    public String getName() {
        return "openweather";
    }

    @Override
    public ForecastContainer get3DayForecast(String cityName) {
        logger.debug("get3DayForecast({})", cityName);

        Response response = openWeatherMapFeignClient.getForecast(cityName, applicationKey);
        List<Forecast> forecasts = response.getList();

        if (forecasts == null || forecasts.size() < 24) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Invalid response from Open Weather Map");
        }


        forecasts.stream()
                .map(forecast -> {

                    Data d = new Data();
                    d.setTemp(forecast.getMain().getTemp());



                })


    }


}
