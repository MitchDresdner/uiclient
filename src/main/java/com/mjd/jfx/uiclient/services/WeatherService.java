package com.mjd.jfx.uiclient.services;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.mjd.jfx.uiclient.beans.Forecast;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.io.IOException;
import java.net.URL;

public class WeatherService implements ITaskService {

    Forecast forecast;

    public void runTask(final Label runnerLabel, final TextArea textArea, final URL pathSpec)  {

        final ObjectMapper mapper = new ObjectMapper();

        // Update the Label on the JavaFx Application Thread
        Platform.runLater(new Runnable() {

            public void run() {
                //Forecast weather = null;
                runnerLabel.setText("Downloading weather data\n");
                try {
                    forecast = mapper.readValue(pathSpec, Forecast.class);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                textArea.appendText(forecast.toString());
                runnerLabel.setText("Weather for " + forecast.getCityName() + "\n");
            }
        });

    }


}

