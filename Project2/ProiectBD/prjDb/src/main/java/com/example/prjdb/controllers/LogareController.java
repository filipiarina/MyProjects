package com.example.prjdb.controllers;

import com.example.prjdb.HelloApplication;
import com.example.prjdb.domain.validate.ValidationException;
import com.example.prjdb.services.Service;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;
import java.util.Optional;

public class LogareController {
    Service service;
    @FXML
    private TextField email;
    @FXML
    private PasswordField parola;

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public void handleLogin(ActionEvent actionEvent) throws ClassNotFoundException, IOException {

        String mail = email.getText();
        String password = parola.getText();
        if(service.Login(mail,password)==0) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("ATENTIE!");
            alert.setHeaderText("Datele introduse sunt invalide. Reincercati!");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get().equals(ButtonType.OK)||result.get().equals(ButtonType.CANCEL))
                alert.close();
        }
        else if(Objects.equals(service.getUtilizatorRepo().findOne(service.Login(mail, password)).get().tip, "student"))
        {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("student-view.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("STUDENT");

            StudentController studController = fxmlLoader.getController();
            studController.setId(service.Login(mail,password));
            studController.setService(service);


            stage.show();
        }
        else if(Objects.equals(service.getUtilizatorRepo().findOne(service.Login(mail, password)).get().tip, "profesor"))
        {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("profesor-view.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("PROFESOR");
            ProfesorController profController = fxmlLoader.getController();
            profController.setId(service.Login(mail,password));
            profController.setService(service);

            stage.show();
        }
        else if(Objects.equals(service.getUtilizatorRepo().findOne(service.Login(mail, password)).get().tip, "administrator")||
                Objects.equals(service.getUtilizatorRepo().findOne(service.Login(mail, password)).get().tip, "super administrator"))
        {
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("admin-view.fxml"));
            Parent root = (Parent) fxmlLoader.load();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.setTitle("ADMIN");
            AdminController adminController = fxmlLoader.getController();
            adminController.setId(service.Login(mail,password));
            adminController.setService(service);

            stage.show();
        }
        email.clear();
        parola.clear();
    }

}

