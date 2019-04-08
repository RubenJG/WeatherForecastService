package com.rubenjg.weatherforecastservice.service.impl;

import com.rubenjg.weatherforecastservice.client.MockFeignClient;
import com.rubenjg.weatherforecastservice.model.calculation.ForecastContainer;
import com.rubenjg.weatherforecastservice.model.mock.MockyResponse;
import com.rubenjg.weatherforecastservice.model.mock.MockyResultList;
import com.rubenjg.weatherforecastservice.service.ForecastDataService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

public class MockForecastDataService implements ForecastDataService {

    private Logger logger = LoggerFactory.getLogger(OpenWeatherMapDataForecastService.class);

    private final MockFeignClient mockFeignClient;

    @Autowired
    public MockForecastDataService(MockFeignClient mockFeignClient) {
        this.mockFeignClient = mockFeignClient;
    }

    @Override
    public String getName() {
        return "mocky";
    }

    @Override
    public ForecastContainer get3DayForecast(String cityName) {
        logger.debug("MockForecastDataService.get3DayForecast({})", cityName);

        MockyResponse response = mockFeignClient.getForecast(cityName);
        List<MockyResultList> forecasts = response.list;

        if (forecasts == null || forecasts.size() < 24) {
            throw new ResponseStatusException(
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    "Invalid response from Open Weather Map");
        }



    }

}
