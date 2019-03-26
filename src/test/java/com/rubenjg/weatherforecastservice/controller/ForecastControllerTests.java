package com.rubenjg.weatherforecastservice.controller;

import com.rubenjg.weatherforecastservice.dto.ForecastDto;
import com.rubenjg.weatherforecastservice.service.ForecastService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class ForecastControllerTests {

    @Mock
    private ForecastService forecastService;

    private ForecastController forecastController;

    @Before
    public void setUp() throws Exception {
        forecastController = new ForecastController(forecastService);
    }

    @Test
    public void getData_Successful() {
        when(forecastService.get3DayForecast(anyString())).thenReturn(new ForecastDto());

        ResponseEntity<ForecastDto> responseEntity = forecastController.getData("test");

        assertNotNull(responseEntity);
        assertNotNull(responseEntity.getBody());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
}
