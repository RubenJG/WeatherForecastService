package com.rubenjg.weatherforecastservice.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rubenjg.weatherforecastservice.client.OpenWeatherMapFeignClient;
import com.rubenjg.weatherforecastservice.dto.ForecastDto;
import com.rubenjg.weatherforecastservice.model.openweathermap.Response;
import com.rubenjg.weatherforecastservice.util.TestUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.server.ResponseStatusException;

import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class OpenWeatherMapForecastServiceTests {

    private static final Random RANDOM = new Random();

    @Autowired
    private ObjectMapper objectMapper;

    @Mock
    private OpenWeatherMapFeignClient openWeatherMapFeignClient;

    private OpenWeatherMapForecastService service;

    @Before
    public void setUp() throws Exception {
        service = new OpenWeatherMapForecastService(
                objectMapper,
                openWeatherMapFeignClient,
                "test");
    }

    @Test
    public void get3DayForecast_Success() {
        when(openWeatherMapFeignClient.getForecast(anyString(), anyString()))
                .thenReturn(TestUtils.mockResponse(24));

        ForecastDto forecastDto = service.get3DayForecast("test");

        assertNotNull(forecastDto);
        assertNotNull(forecastDto.getTemperatureAverage());
        assertEquals(300.0, forecastDto.getTemperatureAverage().getDayTime(), 0.1);
        assertEquals(300.0, forecastDto.getTemperatureAverage().getNightTime(), 0.1);
        assertEquals(300.0, forecastDto.getPressureAverage(), 0.1);
    }

    @Test(expected = ResponseStatusException.class)
    public void get3DayForecast_NullForecasts_ShouldThrowException() {
        when(openWeatherMapFeignClient.getForecast(anyString(), anyString()))
                .thenReturn(new Response());

        service.get3DayForecast("test");
    }

    @Test(expected = ResponseStatusException.class)
    public void get3DayForecast_LessThan24Forecasts_ShouldThrowException() {
        when(openWeatherMapFeignClient.getForecast(anyString(), anyString()))
                .thenReturn(TestUtils.mockResponse(23));

        service.get3DayForecast("test");
    }


}
