package com.rubenjg.weatherforecastservice.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rubenjg.weatherforecastservice.client.OpenWeatherMapFeignClient;
import com.rubenjg.weatherforecastservice.model.openweathermap.Response;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

@RunWith(MockitoJUnitRunner.class)
public class OpenWeatherMapForecastServiceTests {

    private final ObjectMapper objectMapper;

    @MockBean
    private OpenWeatherMapFeignClient openWeatherMapFeignClient;

    private OpenWeatherMapForecastService service;

    @Autowired
    public OpenWeatherMapForecastServiceTests(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Before
    public void setUp() throws Exception {
        service = new OpenWeatherMapForecastService(
                objectMapper,
                openWeatherMapFeignClient,
                "test");
    }

    @Test
    public void get3DayForecast_Success() {

    }

    private Response mockResponse() {
        Response response = null;
        return response;
    }
}
