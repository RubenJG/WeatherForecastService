package com.rubenjg.weatherforecastservice;

import com.google.code.beanmatchers.BeanMatchers;
import com.rubenjg.weatherforecastservice.dto.ForecastDto;
import com.rubenjg.weatherforecastservice.dto.TemperatureDto;
import com.rubenjg.weatherforecastservice.model.openweathermap.City;
import com.rubenjg.weatherforecastservice.model.openweathermap.Forecast;
import com.rubenjg.weatherforecastservice.model.openweathermap.Main;
import com.rubenjg.weatherforecastservice.model.openweathermap.Response;
import org.hamcrest.Matcher;
import org.hamcrest.MatcherAssert;
import org.junit.Before;
import org.junit.Test;

import java.time.OffsetDateTime;

import static com.google.code.beanmatchers.BeanMatchers.*;
import static org.hamcrest.CoreMatchers.allOf;

public class PojoTests {

    @Before
    public void setUp() {
        BeanMatchers.registerValueGenerator(OffsetDateTime::now, OffsetDateTime.class);
    }

    @Test
    public void testForecastDto() {
        // Models
        MatcherAssert.assertThat(City.class, isValidBean());
        MatcherAssert.assertThat(Forecast.class, isValidBean());
        MatcherAssert.assertThat(Main.class, isValidBean());
        MatcherAssert.assertThat(Response.class, isValidBean());

        // Dtos
        MatcherAssert.assertThat(ForecastDto.class, isValidBean());
        MatcherAssert.assertThat(TemperatureDto.class, isValidBean());
    }

    private <T> Matcher<Class<T>> isValidBean() {
        return allOf(
                hasValidBeanConstructor(),
                hasValidGettersAndSetters(),
                hasValidBeanHashCode(),
                hasValidBeanEquals(),
                hasValidBeanToString()
        );
    }
}
