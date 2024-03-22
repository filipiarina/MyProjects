package com.example.prjdb.controllers;

import com.example.prjdb.services.Service;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class StudentController {
    @FXML
    public TabPane studentpane;
    @FXML
    public Tab tabactivitati;
    @FXML
    public Tab tabgrupuri;
    @FXML
    public Tab tabnote;
    @FXML
    public Button inscriereactivitate;
    @FXML
    public ListView<String> tabelgrupuri;
    Service service;
    Integer id;
    @FXML
    private ListView<String> tabelactivitate;

    @FXML
    private ListView<String> tabelnote;
    @FXML
    private Button bye;



    public void setService(Service service) throws ClassNotFoundException {
        this.service = service;
        initModel();
    }

    public void setId(Integer id) {
        this.id = id;
    }
    ObservableList<String> model= FXCollections.observableArrayList();
    ObservableList<String> model2= FXCollections.observableArrayList();
    ObservableList<String> model3= FXCollections.observableArrayList();

    @FXML
    public void initialize() {

        tabelactivitate.setItems(model);
        tabelgrupuri.setItems(model2);
        tabelnote.setItems(model3);
    }
    @FXML
    private void handleBye(ActionEvent event)
    {
        Stage stage = (Stage) bye.getScene().getWindow();
        stage.close();
    }

    private void initModel() throws ClassNotFoundException {
        Iterable<String> messages = service.activitatiStudent(this.id);
        List<String> users = StreamSupport.stream(messages.spliterator(), false).toList();
        model.addAll(users);

        Iterable<String> m = service.grupuriStudent(this.id);
        List<String> u = StreamSupport.stream(m.spliterator(), false).toList();
        model2.addAll(u);

        Iterable<String> mm = service.noteStudent(this.id);
        List<String> uu = StreamSupport.stream(mm.spliterator(), false).toList();
        model3.addAll(uu);
    }

}
