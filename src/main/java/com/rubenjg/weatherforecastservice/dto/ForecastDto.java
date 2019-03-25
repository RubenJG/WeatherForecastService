package com.rubenjg.weatherforecastservice.dto;

import java.time.OffsetDateTime;
import java.util.Objects;

public class ForecastDto {

    private OffsetDateTime today;
    private TemperatureDto temperatureAverage;
    private Double pressureAverage;

    public ForecastDto() {
    }

    public ForecastDto(
            OffsetDateTime today,
            TemperatureDto temperatureAverage,
            Double pressureAverage) {
        this.today = today;
        this.temperatureAverage = temperatureAverage;
        this.pressureAverage = pressureAverage;
    }

    public OffsetDateTime getToday() {
        return today;
    }

    public void setToday(OffsetDateTime today) {
        this.today = today;
    }

    public TemperatureDto getTemperatureAverage() {
        return temperatureAverage;
    }

    public void setTemperatureAverage(TemperatureDto temperatureAverage) {
        this.temperatureAverage = temperatureAverage;
    }

    public Double getPressureAverage() {
        return pressureAverage;
    }

    public void setPressureAverage(Double pressureAverage) {
        this.pressureAverage = pressureAverage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ForecastDto that = (ForecastDto) o;
        return Objects.equals(today, that.today) &&
                Objects.equals(temperatureAverage, that.temperatureAverage) &&
                Objects.equals(pressureAverage, that.pressureAverage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(today, temperatureAverage, pressureAverage);
    }

    @Override
    public String toString() {
        return "ForecastDto{" +
                "today=" + today +
                ", temperatureAverage=" + temperatureAverage +
                ", pressureAverage=" + pressureAverage +
                '}';
    }
}
