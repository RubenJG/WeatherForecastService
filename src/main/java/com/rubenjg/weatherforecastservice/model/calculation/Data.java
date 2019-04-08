package com.rubenjg.weatherforecastservice.model.calculation;

import java.util.Objects;

public class Data {

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
        Data data = (Data) o;
        return Objects.equals(temp, data.temp) &&
                Objects.equals(pressure, data.pressure);
    }

    @Override
    public int hashCode() {
        return Objects.hash(temp, pressure);
    }

    @Override
    public String toString() {
        return "Data{" +
                "temp=" + temp +
                ", pressure=" + pressure +
                '}';
    }
}
