package com.mjd.jfx.uiclient.beans;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.boot.jackson.JsonComponent;
import org.springframework.stereotype.Component;

import java.net.URL;

@Component
@JsonComponent
@JsonIgnoreProperties(ignoreUnknown=true)
public class Forecast {

    private Integer id;

    private String  name = "";
    private Weather weather[];

    private String cod;

    private Long dt;
    private Integer visibility = 0;

    @JsonIgnore
    private URL url;

    //private Coord coord;

    @JsonProperty("name")
    public String getCityName() {
        return name;
    }

    @JsonProperty("name")
    public void setCityName(String name) {
        this.name = name;
    }

    public Weather[] getWeather() {
        return weather;
    }

    public void setWeather(Weather weather[]) {
        this.weather = weather;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("cod")
    public String getSearchCode() {
        return cod;
    }

    @JsonProperty("cod")
    public void setSearchCode(String searchCode) {
        this.cod = cod;
    }

    @JsonProperty("dt")
    public Long getDateTime() {
        return dt;
    }

    @JsonProperty("dt")
    public void setDateTime(Long dt) {
        this.dt = dt;
    }

    public Integer getVisibility() {
        return visibility;
    }

    public void setVisibility(Integer visibility) {
        this.visibility = visibility;
    }

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
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

