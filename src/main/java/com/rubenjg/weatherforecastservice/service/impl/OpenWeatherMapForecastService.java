package com.rubenjg.weatherforecastservice.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rubenjg.weatherforecastservice.dto.ForecastDto;
import com.rubenjg.weatherforecastservice.dto.TemperatureDto;
import com.rubenjg.weatherforecastservice.exception.ForecastException;
import com.rubenjg.weatherforecastservice.model.openweathermap.Forecast;
import com.rubenjg.weatherforecastservice.model.openweathermap.Main;
import com.rubenjg.weatherforecastservice.model.openweathermap.Response;
import com.rubenjg.weatherforecastservice.service.ForecastService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class OpenWeatherMapForecastService implements ForecastService {

    private Logger logger = LoggerFactory.getLogger(OpenWeatherMapForecastService.class);

    private final ObjectMapper objectMapper;

    @Autowired
    public OpenWeatherMapForecastService(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public ForecastDto get3DayForecast(String cityName) throws ForecastException {

        Response response = fakeApi();
        List<Forecast> forecasts = response.getList();

        if (forecasts == null || forecasts.size() != 40) {
            throw new ForecastException("Invalid response from Open Weather Map");
        }

        Double dayTime = 0.0;
        int dayCount = 0;
        Double nightTime = 0.0;
        int nightCount = 0;
        Double pressure = 0.0;

        for (int i = 0; i < 24; i++) {
            Forecast forecast = forecasts.get(i);
            Main main = forecast.getMain();
            Instant instant = Instant.ofEpochSecond(forecast.getDt());
            int hour = instant.atOffset(ZoneOffset.UTC).getHour();
            if (6 <= hour && hour < 18) {
                dayTime += main.getTemp();
                dayCount++;
            } else {
                nightTime += main.getTemp();
                nightCount++;
            }
            pressure += main.getPressure();
        }

        dayTime /= dayCount;
        nightTime /= nightCount;
        pressure /= 24;

        OffsetDateTime today = Instant.ofEpochSecond(forecasts.get(0).getDt()).atOffset(ZoneOffset.UTC);
        TemperatureDto temperatureDto = new TemperatureDto(dayTime, nightTime);
        return new ForecastDto(today, temperatureDto, pressure);
    }

    private Response fakeApi() {
        Response response = null;
        try {
            URL url = getClass().getClassLoader().getResource("forecast.json");
            Objects.requireNonNull(url);
            Path path = Paths.get(url.toURI());

            Stream<String> lines = Files.lines(path);
            String data = lines.collect(Collectors.joining("\n"));
            lines.close();

            response = objectMapper.readValue(data, Response.class);
        } catch (Exception e) {
        }
        return response;
    }
}
