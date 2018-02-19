package com.mjd.jfx.uiclient.beans;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Weather {

    private String description = "";
    private String icon;
    private Integer id;
    private String main;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("main")
    public String getConditions() {
        return main;
    }

    @JsonProperty("main")
    public void setConditions(String main) {
        this.main = main;
    }

}