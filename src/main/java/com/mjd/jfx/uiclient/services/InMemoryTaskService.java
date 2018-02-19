package com.mjd.jfx.uiclient.services;

import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.net.URL;

public class InMemoryTaskService implements ITaskService {

    public void runTask(final Label runnerLabel, final TextArea textArea, final URL pathSpec)  {
        for (int i = 1; i <= 10; i++) {
            try {
                // Get the Status
                final String status = "Processing " + i + " of " + 10;

                // Update the Label on the JavaFx Application Thread
                Platform.runLater(new Runnable() {

                    public void run() {
                        runnerLabel.setText(status);
                    }
                });

                textArea.appendText(status + "\n");

                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
