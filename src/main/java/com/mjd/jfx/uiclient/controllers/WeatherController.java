package com.mjd.jfx.uiclient.controllers;

import com.mjd.jfx.uiclient.ConfigProperties;
import com.mjd.jfx.uiclient.UiClientApplication;
import com.mjd.jfx.uiclient.beans.Forecast;
import com.mjd.jfx.uiclient.services.*;
import com.mjd.jfx.uiclient.views.Stage2View;
import javafx.concurrent.Worker;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import de.felixroske.jfxsupport.FXMLController;
import javafx.event.Event;
import javafx.fxml.FXML;
import org.springframework.beans.factory.annotation.Value;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Calendar;

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

    @FXML
    private Label weatherLabel;

    @FXML
    private TextField apiId;

    @FXML
    private TextField postalCode;

    @FXML
    private ImageView weatherImage;

    @FXML
    private Button stage2Btn;

    @FXML
    private Label weatherDescriptionLabel;

    @FXML
    private Label temperatureLabel;

    @Value("${weather.host}")
    private String hostPath;

    @Value("${weather.apiId}")
    private String appId;

    @Autowired
    ConfigProperties config;

    @Autowired
    Forecast forecast;

    Calendar calendar = Calendar.getInstance();

    @FXML
    public void initialize() {
        postalCode.setText(config.getPostalCode());
        weatherImage.setImage(new Image("http://openweathermap.org/img/w/10d.png"));
        apiId.setText(config.getAppId());
    }

    // Be aware: This is a Spring bean. So we can do the following:
    @Autowired
    private AwesomeActionService actionService;

    @FXML
    void setStage2(ActionEvent event) {
        UiClientApplication.showView(Stage2View.class);
    }

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

            displayAlert("Warning Dialog", "API Id missing or invalid", "Be sure to enter API Id before requesting Weather");

            return;
        }

        config.setAppId( apiId.getText());

        if (postalCode == null || postalCode.getText().length() < 5) {

            displayAlert("Warning Dialog", "Postal Code missing or invalid", "Be sure to enter Postal code before requesting Weather");

            return;
        }

        config.setAppId( apiId.getText());

        try {
            //String pathSpec = config.getHostPath() + "?APPID=" + config.getAppId() + "&units=imperial&zip=" +  config.getPostalCode() + ",us";
            String pathSpec = "http://localhost:3000/weather/1";
            path = new URL(pathSpec);
            forecast.setUrl(path);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        final ForecastService forecastService = new ForecastService();
        forecastService.setPathSpec(path);

        weatherBtn.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                weatherLabel.setText("Getting weather ...");
                logger.info("Invoke weather service: " + forecastService.getState());
                if (forecastService.getState() == Worker.State.READY) {
                    forecastService.start();
                } else {
                    forecastService.restart();
                }
            }
        });

        weatherBtn.disableProperty().bind(forecastService.runningProperty());

        forecastService.setOnSucceeded(new EventHandler<WorkerStateEvent>() {
            public void handle(WorkerStateEvent t) {
                forecast = forecastService.getValue();
                weatherLabel.setText("Weather for " + forecast.getCityName() + "  " + calendar.getTime().toString());
                weatherDescriptionLabel.setText(forecast.getWeather()[0].getDescription());
                temperatureLabel.setText("Current " + forecast.getMain().getTemperature() + "F" + "  Low "
                        + forecast.getMain().getMinTemperature() + " / " + forecast.getMain().getMaxTemperature() + " High" );
                weatherImage.setImage(new Image("http://openweathermap.org/img/w/" + forecast.getWeather()[0].getIcon() + ".png"));

                calendar.setTimeInMillis(forecast.getDateTime());

                logger.info("On Succeed callback - weather for " + forecast.getCityName() + "  " + calendar.getTime().toString());
            }
        });

        // runtask(new WeatherService(), textArea, path);
    }



    private void displayAlert(String title, String hdr, String detail) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(title);
        alert.setHeaderText(hdr);
        alert.setContentText(detail);

        alert.showAndWait();
    }

    void runtask (final ITaskService service, final TextArea textArea, final URL pathSpec) {

        // Create a Runnable
        Runnable task = new Runnable() {
            public void run() {
                ITaskService runner = service;
                runner.runTask(weatherLabel, textArea, pathSpec);
            }
        };

        // Run the task in a background thread
        Thread backgroundThread = new Thread(task);
        // Terminate the running thread if the application exits
        backgroundThread.setDaemon(true);
        // Start the thread
        backgroundThread.start();
    }




//    private static class WeatherService extends Service<String> {
//        private URL url = null;
//        private Forecast weather = null;
//
//        public final void setUrl(URL value) {
//            url = value;
//        }
//
//        public final URL getUrl() {
//            return url;
//        }
//
//        public final Forecast getWeather() { return weather; }
//
//        protected Task<String> createTask() {
//            final URL _url = getUrl();
//            return new Task<String>() {
//                protected String call()
//                        throws IOException, MalformedURLException {
//                    ObjectMapper mapper = new ObjectMapper();
//                    String result = "Get Json completes";
//                    try {
//                        weather = mapper.readValue(url, Forecast.class);
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }
//                    return result;
//                }
//            };
//        }
//    }

}
