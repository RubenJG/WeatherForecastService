package com.rubenjg.weatherforecastservice.controller;

import com.rubenjg.weatherforecastservice.dto.ForecastDto;
import com.rubenjg.weatherforecastservice.service.ForecastDataService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Size;
import java.util.List;

@Validated
@RestController
public class ForecastController {

    private final List<ForecastDataService> forecastDataServices;

    @Autowired
    public ForecastController(List<ForecastDataService> forecastDataServices) {
        this.forecastDataServices = forecastDataServices;
    }

    @ApiOperation("Get forecast information for the next 3 days.")
    @GetMapping("/data")
    public ResponseEntity<ForecastDto> getData(
            @ApiParam(value = "City name. Between 1 and 100 characters.", required = true)
            @RequestParam("cityName")
            @Size(min = 1, max = 100) String cityName,
            @RequestParam("provider")
                    String provider) {

        ForecastDto forecastDto = forecastDataServices.stream()
                .filter(forecastDataService -> forecastDataService.getName() == provider)
                .findFirst()
                .map(forecastDataService -> forecastDataService.get3DayForecast(cityName))
                .orElse(null);

        return new ResponseEntity<>(forecastDto, HttpStatus.OK);
    }
}
