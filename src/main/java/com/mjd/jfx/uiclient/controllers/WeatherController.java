package com.mjd.jfx.uiclient.controllers;

import com.mjd.jfx.uiclient.services.AwesomeActionService;
import com.mjd.jfx.uiclient.services.ITaskService;
import com.mjd.jfx.uiclient.services.InMemoryTaskService;
import com.mjd.jfx.uiclient.services.WeatherService;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import de.felixroske.jfxsupport.FXMLController;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

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

        runtask(new InMemoryTaskService(), textArea);
    }

    @FXML
    void fetchWeather(ActionEvent event) {
        runtask(new WeatherService(), textArea);
    }

    void runtask (ITaskService service, TextArea textArea) {

        // Create a Runnable
        Runnable task = new Runnable() {
            public void run() {
                ITaskService runner = service;
                runner.runTask(runnerLabel, textArea);
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
