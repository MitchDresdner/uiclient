package com.mjd.jfx.uiclient.services;

import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.net.URL;

public interface ITaskService {
    void runTask(Label runnerLabel, TextArea textArea, URL pathSpec);
}
