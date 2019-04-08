package com.rubenjg.weatherforecastservice.model.calculation;

import java.util.List;
import java.util.Objects;

public class ForecastContainer {

    private List<Forecast> list;

    public List<Forecast> getList() {
        return list;
    }

    public void setList(List<Forecast> list) {
        this.list = list;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ForecastContainer forecastContainer = (ForecastContainer) o;
        return Objects.equals(list, forecastContainer.list);
    }

    @Override
    public int hashCode() {
        return Objects.hash(list);
    }

    @Override
    public String toString() {
        return "ForecastContainer{" +
                "list=" + list +
                '}';
    }
}
