package com.rubenjg.weatherforecastservice.service.impl;

import com.rubenjg.weatherforecastservice.dto.ForecastDto;
import com.rubenjg.weatherforecastservice.dto.TemperatureDto;
import com.rubenjg.weatherforecastservice.model.calculation.Data;
import com.rubenjg.weatherforecastservice.model.calculation.ForecastContainer;
import com.rubenjg.weatherforecastservice.service.ForecastCalculationService;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;

@Service
public class DefaultForecastCalculationService implements ForecastCalculationService {


    @Override
    public ForecastDto calculate3DayForecast(ForecastContainer forecastContainer) {
        CounterHelper counterHelper = new CounterHelper();

        Long start = OffsetDateTime.now().toEpochSecond();
        Long end = OffsetDateTime.now().plusDays(3).toEpochSecond();

        forecastContainer.getList()
                .stream()
                .filter(f -> start <= f.getDt() && f.getDt() <= end)
                .forEach(f -> {
                    Data data = f.getData();
                    Instant instant = Instant.ofEpochSecond(f.getDt());
                    int hour = instant.atOffset(ZoneOffset.UTC).getHour();
                    if (6 <= hour && hour < 18) {
                        counterHelper.increaseDayTime(data.getTemp());
                        counterHelper.increaseDayCount(1);
                    } else {
                        counterHelper.increaseNightTime(data.getTemp());
                        counterHelper.increaseNightCount(1);
                    }
                    counterHelper.increasePressure(data.getPressure());
                });

        Double dayTime = counterHelper.getDayTime() / counterHelper.getDayCount();
        Double nightTime = counterHelper.getNightTime() / counterHelper.getNightCount();
        Double pressure = counterHelper.getPressure() / (counterHelper.getDayCount() + counterHelper.getNightCount());

        OffsetDateTime today = Instant.ofEpochSecond(forecastContainer.getList().get(0).getDt()).atOffset(ZoneOffset.UTC);
        TemperatureDto temperatureDto = new TemperatureDto(dayTime, nightTime);
        return new ForecastDto(today, temperatureDto, pressure);
    }

    public class CounterHelper {
        Double dayTime = 0.0;
        int dayCount = 0;
        Double nightTime = 0.0;
        int nightCount = 0;
        Double pressure = 0.0;

        public Double getDayTime() {
            return dayTime;
        }

        public void increaseDayTime(Double dayTime) {
            this.dayTime += dayTime;
        }

        public int getDayCount() {
            return dayCount;
        }

        public void increaseDayCount(int dayCount) {
            this.dayCount += dayCount;
        }

        public Double getNightTime() {
            return nightTime;
        }

        public void increaseNightTime(Double nightTime) {
            this.nightTime += nightTime;
        }

        public int getNightCount() {
            return nightCount;
        }

        public void increaseNightCount(int nightCount) {
            this.nightCount += nightCount;
        }

        public Double getPressure() {
            return pressure;
        }

        public void increasePressure(Double pressure) {
            this.pressure += pressure;
        }
    }
}
