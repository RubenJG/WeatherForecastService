package com.rubenjg.weatherforecastservice.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rubenjg.weatherforecastservice.client.OpenWeatherMapFeignClient;
import com.rubenjg.weatherforecastservice.dto.ForecastDto;
import com.rubenjg.weatherforecastservice.model.openweathermap.Forecast;
import com.rubenjg.weatherforecastservice.model.openweathermap.Main;
import com.rubenjg.weatherforecastservice.model.openweathermap.Response;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
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
                .thenReturn(mockResponse(24));

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
                .thenReturn(mockResponse(23));

        service.get3DayForecast("test");
    }

    private Response mockResponse(int n) {
        Response response = new Response();
        response.setList(mockForecasts(n));
        return response;
    }

    private List<Forecast> mockForecasts(int n) {
        List<Forecast> forecasts = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            Instant instant = Instant.now().plus(3 * i, ChronoUnit.HOURS);
            Forecast forecast = new Forecast();
            forecast.setDt(instant.toEpochMilli() / 1000);
            Main main = new Main();
            main.setTemp(300.0);
            main.setPressure(300.0);
            forecast.setMain(main);
            forecasts.add(forecast);
        }
        return forecasts;
    }

}
