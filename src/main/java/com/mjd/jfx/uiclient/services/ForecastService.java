package com.mjd.jfx.uiclient.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mjd.jfx.uiclient.beans.Forecast;
import javafx.concurrent.Service;
import javafx.concurrent.Task;

import java.io.IOException;
import java.net.URL;

public class ForecastService  extends Service<Forecast> {

    Forecast forecast = null;
    final ObjectMapper mapper = new ObjectMapper();

    URL pathSpec;

    public void setPathSpec(URL pathSpec) {
        this.pathSpec = pathSpec;
    }

    @Override protected Task createTask() {
        return new Task<Forecast>() {
            @Override protected Forecast call() throws Exception {
                try {
                    forecast = mapper.readValue(pathSpec, Forecast.class);
                    //JsonNode node = mapper.readTree(pathSpec);

                } catch (IOException e) {
                    e.printStackTrace();
                }
                return forecast;
            }
        };
    }

}
