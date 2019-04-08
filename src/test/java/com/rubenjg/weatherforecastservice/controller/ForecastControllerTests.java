package com.rubenjg.weatherforecastservice.controller;

import com.rubenjg.weatherforecastservice.dto.ForecastDto;
import com.rubenjg.weatherforecastservice.service.ForecastDataService;
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
    private ForecastDataService forecastDataService;

    private ForecastController forecastController;

    @Before
    public void setUp() throws Exception {
        forecastController = new ForecastController(forecastDataService);
    }

    @Test
    public void getData_Successful() {
        when(forecastDataService.get3DayForecast(anyString())).thenReturn(new ForecastDto());

        ResponseEntity<ForecastDto> responseEntity = forecastController.getData("test");

        assertNotNull(responseEntity);
        assertNotNull(responseEntity.getBody());
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
}
