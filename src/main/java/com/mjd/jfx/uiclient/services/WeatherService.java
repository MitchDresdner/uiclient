package com.mjd.jfx.uiclient.services;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.mjd.jfx.uiclient.beans.Forecast;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.io.IOException;
import java.net.URL;

public class WeatherService implements ITaskService {
    @Override
    public void runTask(Label runnerLabel, TextArea textArea)  {

        ObjectMapper mapper = new ObjectMapper();

        // Update the Label on the JavaFx Application Thread
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                Forecast weather = null;
                runnerLabel.setText("Downloading weather data\n");
                try {
                    weather = mapper.readValue(new URL("http://api.openweathermap.org/data/2.5/weather?APPID=your-api-key&units=imperial&zip=20151,us"), Forecast.class);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                textArea.appendText(weather.toString());
                runnerLabel.setText("Weather for " + weather.getName() + "\n");
            }
        });

    }
}

