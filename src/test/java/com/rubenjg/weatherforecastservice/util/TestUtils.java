package com.rubenjg.weatherforecastservice.util;

import com.rubenjg.weatherforecastservice.model.openweathermap.Forecast;
import com.rubenjg.weatherforecastservice.model.openweathermap.Main;
import com.rubenjg.weatherforecastservice.model.openweathermap.Response;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class TestUtils {

    public static Response mockResponse(int n) {
        Response response = new Response();
        response.setList(mockForecasts(n));
        return response;
    }

    public static List<Forecast> mockForecasts(int n) {
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
