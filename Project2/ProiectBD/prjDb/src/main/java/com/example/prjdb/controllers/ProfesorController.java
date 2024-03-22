package com.example.prjdb.controllers;

import com.example.prjdb.services.Service;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.util.List;
import java.util.stream.StreamSupport;

public class ProfesorController {
    @FXML
    public ListView<String> listanote;
    @FXML
    public ListView datepersonale;
    @FXML
    private Button bye;
    Service service;
    Integer id;


    public void setService(Service service) throws ClassNotFoundException {
        this.service = service;
        initModel();
    }

    public void setId(Integer id) {
        this.id = id;
    }

    ObservableList<String> model= FXCollections.observableArrayList();
    ObservableList<String> model2= FXCollections.observableArrayList();

    @FXML
    private void handleBye(ActionEvent event)
    {
        Stage stage = (Stage) bye.getScene().getWindow();
        stage.close();
    }

    @FXML
    public void initialize() {

        listanote.setItems(model);
        datepersonale.setItems(model2);
    }

    private void initModel() throws ClassNotFoundException {
        Iterable<String> messages = service.Catalog(this.id);
        List<String> users = StreamSupport.stream(messages.spliterator(), false).toList();
        model.addAll(users);

        Iterable<String> m = service.DatePersonaleProf(this.id);
        List<String> u = StreamSupport.stream(m.spliterator(), false).toList();
        model2.addAll(u);
    }

}
