package com.rubenjg.weatherforecastservice.client;

import com.rubenjg.weatherforecastservice.model.openweathermap.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "openWeatherMapFeignClient", url = "${service.open-weather-map.url}")
public interface OpenWeatherMapFeignClient {

    @GetMapping(value = "/data/2.5/forecast", produces = "application/json")
    Response getForecast(
            @RequestParam("q") String cityName,
            @RequestParam("appid") String applicationKey);
}
