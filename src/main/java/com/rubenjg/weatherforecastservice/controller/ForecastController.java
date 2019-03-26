package com.rubenjg.weatherforecastservice.controller;

import com.rubenjg.weatherforecastservice.dto.ForecastDto;
import com.rubenjg.weatherforecastservice.service.ForecastService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Size;

@RestController("/data")
@Validated
public class ForecastController {

    private final ForecastService forecastService;

    @Autowired
    public ForecastController(ForecastService forecastService) {
        this.forecastService = forecastService;
    }

    @GetMapping()
    public ResponseEntity<ForecastDto> getData(
            @RequestParam("cityName")
            @Size(min = 1, max = 100) String cityName) {
        ForecastDto forecastDto = forecastService.get3DayForecast(cityName);
        return new ResponseEntity<>(forecastDto, HttpStatus.OK);
    }
}
