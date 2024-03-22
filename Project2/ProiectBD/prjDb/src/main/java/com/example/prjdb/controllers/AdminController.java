package com.example.prjdb.controllers;

import com.example.prjdb.services.Service;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.StreamSupport;

public class AdminController {
    public ListView<String> cursuri;
    public ListView<String> users;
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
    public void initialize() {

        users.setItems(model);
        cursuri.setItems(model2);
    }
    @FXML
    private void handleBye(ActionEvent event)
    {
        Stage stage = (Stage) bye.getScene().getWindow();
        stage.close();
    }

    private void initModel() throws ClassNotFoundException {
        Iterable<String> messages = new ArrayList<String>();
        if(Objects.equals(service.getAdministratorRepo().findOne(this.id).get().getTip(), "Admin"))
            messages = service.getUsers(this.id);
        else
            messages = service.getAllUsers(this.id);
        List<String> uu = StreamSupport.stream(messages.spliterator(), false).toList();
        model.addAll(uu);

        Iterable<String> m = service.getCursuri(this.id);
        List<String> u = StreamSupport.stream(m.spliterator(), false).toList();
        model2.addAll(u);
    }
}
