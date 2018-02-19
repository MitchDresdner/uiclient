package com.mjd.jfx.uiclient.services;

import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.net.URL;

public interface ITaskService {
    <T> void runTask(final Label runnerLabel, final TextArea textArea, final URL pathSpec);
}

