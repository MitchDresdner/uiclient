package com.mjd.jfx.uiclient.controllers;

import com.mjd.jfx.uiclient.ConfigProperties;
import com.mjd.jfx.uiclient.services.AwesomeActionService;
import com.mjd.jfx.uiclient.services.ITaskService;
import com.mjd.jfx.uiclient.services.InMemoryTaskService;
import com.mjd.jfx.uiclient.services.WeatherService;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import de.felixroske.jfxsupport.FXMLController;
import javafx.event.Event;
import javafx.fxml.FXML;
import org.springframework.beans.factory.annotation.Value;

import java.net.MalformedURLException;
import java.net.URL;

@FXMLController
public class WeatherController {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @FXML
    private Label helloLabel;

    @FXML
    private TextField nameField;

    @FXML
    private Label runnerLabel;

    @FXML
    private Button runnerButton;

    @FXML
    private TextArea  textArea;

    @FXML
    private Button weatherBtn;

    @Value("${weather.host}")
    private String hostPath;

    @Value("${weather.apiId}")
    private String appId;

    @FXML
    private TextField apiId;

    @Autowired
    ConfigProperties config;

    // Be aware: This is a Spring bean. So we can do the following:
    @Autowired
    private AwesomeActionService actionService;

    @FXML
    private void setHelloText(final Event event) {
        final String textToBeShown = actionService.processName(nameField.getText());
        helloLabel.setText(textToBeShown);

        logger.debug("This is a debug message");
        logger.info("This is an info message");
        logger.warn("This is a warn message");
        logger.error("This is an error message");
    }

    @FXML
    void startTask(ActionEvent event) {

        runtask(new InMemoryTaskService(), textArea, null);
    }

    @FXML
    void fetchWeather(ActionEvent event) {
        URL path = null;

        if (apiId == null || apiId.getText().length() < 32) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning Dialog");
            alert.setHeaderText("API Id missing or invalid");
            alert.setContentText("Be sure to enter API Id before requesting Weather");

            alert.showAndWait();

            return;
        }

        config.setAppId( apiId.getText());

        try {
            String pathSpec = config.getHostPath() + "?APPID=" + config.getAppId() + "&units=imperial&zip=20151,us";
            path = new URL(pathSpec);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        runtask(new WeatherService(), textArea, path);
    }

    void runtask (ITaskService service, TextArea textArea, URL pathSpec) {

        // Create a Runnable
        Runnable task = new Runnable() {
            public void run() {
                ITaskService runner = service;
                runner.runTask(runnerLabel, textArea, pathSpec);
            }
        };

        // Run the task in a background thread
        Thread backgroundThread = new Thread(task);
        // Terminate the running thread if the application exits
        backgroundThread.setDaemon(true);
        // Start the thread
        backgroundThread.start();
    }

}
