package com.rubenjg.weatherforecastservice.model.openweathermap;

import java.util.List;
import java.util.Objects;

public class Response {

    private City city;
    private List<Forecast> list;

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

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
        Response response = (Response) o;
        return Objects.equals(city, response.city) &&
                Objects.equals(list, response.list);
    }

    @Override
    public int hashCode() {
        return Objects.hash(city, list);
    }

    @Override
    public String toString() {
        return "Response{" +
                "city=" + city +
                ", list=" + list +
                '}';
    }
}
