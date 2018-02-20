package com.mjd.jfx.uiclient.beans;

import com.fasterxml.jackson.annotation.JsonProperty;

public class MainStats {

    private Float temp;
    private Float humidity;
    private Integer pressure;
    private Float temp_max;
    private Float temp_min;

    @JsonProperty("temp")
    public Float getTemperature() {
        return temp;
    }

    @JsonProperty("temp")
    public void setTemperature(Float temp) {
        this.temp = temp;
    }

    public Float getHumidity() {
        return humidity;
    }

    public void setHumidity(Float humidity) {
        this.humidity = humidity;
    }

    public Integer getPressure() {
        return pressure;
    }

    public void setPressure(Integer pressure) {
        this.pressure = pressure;
    }

    public Float getMaxTemperature() {
        return temp_max;
    }

    @JsonProperty("temp_max")
    public void setMaxTemperature(Float temp_max) {
        this.temp_max = temp_max;
    }

    public Float getMinTemperature() {
        return temp_min;
    }

    @JsonProperty("temp_min")
    public void setMinTemperature(Float temp_min) {
        this.temp_min = temp_min;
    }
}
