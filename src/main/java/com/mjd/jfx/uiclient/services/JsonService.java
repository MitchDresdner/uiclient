package com.mjd.jfx.uiclient.services;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mjd.jfx.uiclient.ConfigProperties;
import com.mjd.jfx.uiclient.beans.Forecast;
import com.mjd.jfx.uiclient.services.ITaskService;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;

public class JsonService implements ITaskService {

    @Autowired
    ConfigProperties config;

    public <T> void runTask(final Label runnerLabel, final TextArea textArea, final URL pathSpec) {         //}, final ObservableList<T> observableList) {

        final FirstLineService service = new FirstLineService();

        service.setUrl(pathSpec);

        service.setOnSucceeded(new EventHandler<WorkerStateEvent>() {

            public void handle(WorkerStateEvent t) {
                System.out.println("done:" + t.getSource().getValue());
                Forecast weather = service.getWeather();
                if (weather != null) {
                    runnerLabel.setText("FOIA results\n");
                    textArea.appendText(weather.toString() + "\n");
                    //observableList.add((T) weather);
                } else {
                    textArea.appendText("No results for request\n");
                    runnerLabel.setText("Error Occurred\n");
                }
            }
        });


        service.start();
    }

    private static class FirstLineService extends Service<String> {
        private URL url = null;
        private Forecast weather = null;

        public final void setUrl(URL value) {
            url = value;
        }

        public final URL getUrl() {
            return url;
        }

        public final Forecast getWeather() { return weather; }

        protected Task<String> createTask() {
            final URL _url = getUrl();
            return new Task<String>() {
                protected String call()
                        throws IOException, MalformedURLException {
                    ObjectMapper mapper = new ObjectMapper();
                    String result = "Get Json completes";
                    try {
                        weather = mapper.readValue(url, Forecast.class);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return result;
                }
            };
        }
    }
}
