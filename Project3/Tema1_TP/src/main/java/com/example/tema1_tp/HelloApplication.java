package com.example.tema1_tp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    public static void main(String[] args) {
        Application.launch(args);
    }

    public void start(Stage primaryStage) throws Exception {
        Pane mainPane = (Pane)FXMLLoader.load(HelloApplication.class.getResource("aaa.fxml"));
        primaryStage.setScene(new Scene(mainPane));
        primaryStage.setTitle("Calculator");
        primaryStage.show();
    }
}