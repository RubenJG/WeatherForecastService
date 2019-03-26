package com.rubenjg.weatherforecastservice.client;

import org.springframework.cloud.openfeign.FeignClient;

@FeignClient(name = "openWeatherMapFeignClient", url = "${service.open-weather-map.url}")
public class OpenWeatherMapFeignClient {
    
}
