package com.rubenjg.weatherforecastservice.client;

import com.rubenjg.weatherforecastservice.model.mock.MockyResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "MockFeignClient", url = "http://www.mocky.io")
public interface MockFeignClient {

    @GetMapping(value = "/v2/5bcde18f2f00004600c85611", produces = "application/json")
    MockyResponse getForecast(@RequestParam("q") String cityName);
}
