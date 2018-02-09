package org.cms.uitester.services;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import org.cms.uitester.beans.Foia;
import org.cms.uitester.beans.Forecast;

public class JsonService implements ITaskService {
    @Override
    public <T> void runTask(Label runnerLabel, TextArea textArea, ObservableList<T> observableList) {

        FirstLineService service = new FirstLineService();
        //service.setUrl("http://api.openweathermap.org/data/2.5/weather?APPID=9c191c58be6ceca9a392f8f975cb7da7&units=imperial&zip=20151,us");
        service.setUrl("http://10.193.183.187:8082/relatedContents/FOIACases");

        service.setOnSucceeded(new EventHandler<WorkerStateEvent>() {

            @Override
            public void handle(WorkerStateEvent t) {
                System.out.println("done:" + t.getSource().getValue());
                //Forecast weather = service.getWeather();
                Foia[] foia = service.getFoia();
                if (foia != null) {
                    runnerLabel.setText("FOIA results\n");
                    for (Foia foiaRec : foia) {
                        textArea.appendText(foiaRec.toString() + "\n");
                        observableList.add((T) foiaRec);
                    }

                } else {
                    textArea.appendText("No results for request\n");
                    runnerLabel.setText("Error Occurred\n");
                }
            }
        });


        service.start();
    }

    private static class FirstLineService extends Service<String> {
        private StringProperty url = new SimpleStringProperty();
        private Forecast weather = null;
        private Foia[] foia = null;

        public final void setUrl(String value) {
            url.set(value);
        }

        public final String getUrl() {
            return url.get();
        }

        public final StringProperty urlProperty() {
            return url;
        }

        public final Forecast getWeather() { return weather; }

        public final Foia[] getFoia() { return foia; }

        protected Task<String> createTask() {
            final String _url = getUrl();
            return new Task<String>() {
                protected String call()
                        throws IOException, MalformedURLException {
                    ObjectMapper mapper = new ObjectMapper();
                    String result = "Get Json completes";
                    try {
                        //weather = mapper.readValue(new URL(_url), Forecast.class);
                        //Car[] cars2 = objectMapper.readValue(jsonArray, Car[].class);
                        foia = mapper.readValue(new URL(_url), Foia[].class);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    return result;
                }
            };
        }
    }
}
