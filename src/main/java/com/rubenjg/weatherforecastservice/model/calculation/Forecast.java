package com.rubenjg.weatherforecastservice.model.calculation;

import java.util.Objects;

public class Forecast {

    private Long dt;
    private Data data;

    public Long getDt() {
        return dt;
    }

    public void setDt(Long dt) {
        this.dt = dt;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Forecast forecast = (Forecast) o;
        return Objects.equals(dt, forecast.dt) &&
                Objects.equals(data, forecast.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dt, data);
    }

    @Override
    public String toString() {
        return "Forecast{" +
                "dt=" + dt +
                ", data=" + data +
                '}';
    }
}
