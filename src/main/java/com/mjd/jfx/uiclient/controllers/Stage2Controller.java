package com.mjd.jfx.uiclient.controllers;

import com.mjd.jfx.uiclient.UiClientApplication;
import com.mjd.jfx.uiclient.views.WeatherView;
import de.felixroske.jfxsupport.FXMLController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

@FXMLController
public class Stage2Controller {

    @FXML
    private Button weatherBtn;

    @FXML
    private Label weatherLabel;

    @FXML
    private ImageView weatherImage;

    @FXML
    void weatherStage(ActionEvent event) {
        UiClientApplication.showView(WeatherView.class);
    }

}
