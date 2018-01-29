package com.mjd.jfx.uiclient.beans;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.boot.jackson.JsonComponent;

@JsonComponent
@JsonIgnoreProperties(ignoreUnknown=true)
public class Forecast {

    private Integer id;

    @JsonProperty("name")
    private String  cityName;
    //private Weather weather;

    @JsonProperty("cod")
    private String searchCode;

    @JsonProperty("dt")
    private Long dateTime;
    private Integer visibility;

    //private Coord coord;

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String name) {
        this.cityName = cityName;
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

    public String getSearchCode() {
        return searchCode;
    }

    public void setSearchCode(String searchCode) {
        this.searchCode = searchCode;
    }

    public Long getDateTime() {
        return dateTime;
    }

    public void setDateTime(Long dt) {
        this.dateTime = dateTime;
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
                "name='" + cityName + '\'' +
                ", visibility='" + visibility.toString() + '\'' +
//                ", description=" + weather.getDescription() +
                '}';
    }

}

