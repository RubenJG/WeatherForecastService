package com.rubenjg.weatherforecastservice.model.openweathermap;

import java.util.Objects;

public class Forecast {

    private Long dt;
    private Main main;

    public Long getDt() {
        return dt;
    }

    public void setDt(Long dt) {
        this.dt = dt;
    }

    public Main getMain() {
        return main;
    }

    public void setMain(Main main) {
        this.main = main;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Forecast forecast = (Forecast) o;
        return Objects.equals(dt, forecast.dt) &&
                Objects.equals(main, forecast.main);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dt, main);
    }

    @Override
    public String toString() {
        return "Forecast{" +
                "dt=" + dt +
                ", main=" + main +
                '}';
    }
}
