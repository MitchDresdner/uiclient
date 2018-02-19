package com.mjd.jfx.uiclient.controllers;

import com.mjd.jfx.uiclient.UiClientApplication;
import com.mjd.jfx.uiclient.views.Stage2View;
import com.mjd.jfx.uiclient.views.WeatherView;
import de.felixroske.jfxsupport.FXMLController;
import javafx.event.Event;

@FXMLController
public class ViewController {

    public void showFirstView(Event event) {
        UiClientApplication.showView(WeatherView.class);
    }

    public void showSecondView(Event event) {
        UiClientApplication.showView(Stage2View.class);
    }
}
