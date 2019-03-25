package com.rubenjg.weatherforecastservice.dto;

import java.util.Objects;

public class TemperatureDto {

    private Double dayTime;
    private Double nightTime;

    public TemperatureDto() {
    }

    public TemperatureDto(Double dayTime, Double nightTime) {
        this.dayTime = dayTime;
        this.nightTime = nightTime;
    }

    public Double getDayTime() {
        return dayTime;
    }

    public void setDayTime(Double dayTime) {
        this.dayTime = dayTime;
    }

    public Double getNightTime() {
        return nightTime;
    }

    public void setNightTime(Double nightTime) {
        this.nightTime = nightTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TemperatureDto that = (TemperatureDto) o;
        return Objects.equals(dayTime, that.dayTime) &&
                Objects.equals(nightTime, that.nightTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dayTime, nightTime);
    }

    @Override
    public String toString() {
        return "TemperatureDto{" +
                "dayTime=" + dayTime +
                ", nightTime=" + nightTime +
                '}';
    }
}
