package com.mjd.jfx.uiclient.beans;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.boot.jackson.JsonComponent;

@JsonComponent
@JsonIgnoreProperties(ignoreUnknown=true)
public class Forecast {

    private Integer id;
    private String  name;
    //private Weather weather;
    private String cod;
    private Long dt;
    private Integer visibility;

    //private Coord coord;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

//    public Weather getWeather() {
//        return weather;
//    }
//
//    public void setWeather(Weather weather) {
//        this.weather = weather;
//    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCod() {
        return cod;
    }

    public void setCod(String cod) {
        this.cod = cod;
    }

    public Long getDt() {
        return dt;
    }

    public void setDt(Long dt) {
        this.dt = dt;
    }

    public Integer getVisibility() {
        return visibility;
    }

    public void setVisibility(Integer visibility) {
        this.visibility = visibility;
    }

    @Override
    public String toString() {
        return "Forecast{" +
                "name='" + name + '\'' +
                ", visibility='" + visibility.toString() + '\'' +
//                ", description=" + weather.getDescription() +
                '}';
    }

}

