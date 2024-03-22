package com.example.prjdb.controllers;

import com.example.prjdb.HelloApplication;
import com.example.prjdb.services.Service;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;

public class StartController {
    Service service;
    @FXML
    private Button prof;
    @FXML
    private Button stud;
    @FXML
    private Button admin;

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }
    @FXML
    public void initialize() {

    }
    public void handleButton(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("login-view.fxml"));
        Parent root = (Parent) fxmlLoader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("LOGARE");
        LogareController adaugaController = fxmlLoader.getController();
        adaugaController.setService(service);
        stage.show();
    }

}
