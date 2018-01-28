package com.mjd.jfx.uiclient;

import com.mjd.jfx.uiclient.views.WeatherView;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import de.felixroske.jfxsupport.AbstractJavaFxApplicationSupport;

@SpringBootApplication
public class UiClientApplication extends AbstractJavaFxApplicationSupport {

	public static void main(String[] args) {

		launch(UiClientApplication.class, WeatherView.class, args);

	}
}
