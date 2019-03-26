package com.rubenjg.weatherforecastservice.model.openweathermap;

import java.util.Objects;

public class Main {

    private Double temp;
    private Double pressure;

    public Double getTemp() {
        return temp;
    }

    public void setTemp(Double temp) {
        this.temp = temp;
    }

    public Double getPressure() {
        return pressure;
    }

    public void setPressure(Double pressure) {
        this.pressure = pressure;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Main main = (Main) o;
        return Objects.equals(temp, main.temp) &&
                Objects.equals(pressure, main.pressure);
    }

    @Override
    public int hashCode() {
        return Objects.hash(temp, pressure);
    }

    @Override
    public String toString() {
        return "Main{" +
                "temp=" + temp +
                ", pressure=" + pressure +
                '}';
    }
}
