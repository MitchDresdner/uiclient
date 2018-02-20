package com.mjd.jfx.uiclient.beans;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Wind {

    private Float speed;
    private Integer deg;

    public Float getSpeed() {
        return speed;
    }

    public void setSpeed(Float speed) {
        this.speed = speed;
    }


    public Integer getDegrees() {
        return deg;
    }

    @JsonProperty("deg")
    public void setDegrees(Integer deg) {
        this.deg = deg;
    }
}
