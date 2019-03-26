package com.rubenjg.weatherforecastservice.controller;

import com.rubenjg.weatherforecastservice.client.OpenWeatherMapFeignClient;
import com.rubenjg.weatherforecastservice.util.TestUtils;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.util.NestedServletException;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ForecastControllerIntegrationTests {

    @MockBean
    private OpenWeatherMapFeignClient openWeatherMapFeignClient;

    @Autowired
    private WebApplicationContext wac;

    private MockMvc mockMvc;

    @Before
    public void setup() throws Exception {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
    }

    @Test
    public void getData_24Forecasts_200Ok() throws Exception {

        when(openWeatherMapFeignClient.getForecast(anyString(), anyString())).thenReturn(TestUtils.mockResponse(24));

        this.mockMvc.perform(get("/data?cityName=test"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.temperatureAverage.dayTime").value(300.0))
                .andExpect(jsonPath("$.temperatureAverage.nightTime").value(300.0))
                .andExpect(jsonPath("$.pressureAverage").value(300.0));
    }

    @Test
    public void getData_23Forecasts_500InternalServerError() throws Exception {
        when(openWeatherMapFeignClient.getForecast(anyString(), anyString())).thenReturn(TestUtils.mockResponse(23));

        this.mockMvc.perform(get("/data?cityName=test"))
                .andDo(print())
                .andExpect(status().isInternalServerError());
    }

    @Test
    public void getData_NoCityName_400BadRequest() throws Exception {
        this.mockMvc.perform(get("/data"))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test(expected = NestedServletException.class)
    public void getData_InvalidCityName_ShouldThrowConstraintViolationException() throws Exception {
        StringBuffer cityNameSb = new StringBuffer();
        for (int i = 0; i < 101; ++i) {
            cityNameSb.append("a");
        }
        String cityName = cityNameSb.toString();

        this.mockMvc.perform(get(String.format("/data?cityName=%s", cityName)));
    }

}
