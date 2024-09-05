package org.example;

import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage stage) {
        SessionHandler sessionHandler = new SessionHandler(stage);
        sessionHandler.renderUI();
    }

    public static void main(String[] args) {
        Application.launch(args);
    }
}