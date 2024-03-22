package com.example.prjdb;

import com.example.prjdb.controllers.StartController;
import com.example.prjdb.domain.*;
import com.example.prjdb.repository.Repo;
import com.example.prjdb.repository.dbRepo.*;
import com.example.prjdb.services.Service;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import org.controlsfx.control.action.Action;

import java.io.IOException;

public class HelloApplication extends Application {
    Service service;
    @Override
    public void start(Stage stage) throws IOException, ClassNotFoundException {

        String username="root";
        String pasword="rezistLAfacultate!2021";
        String url="jdbc:mysql://localhost:3306/prj?useSSL=false";
        String driver = "com.mysql.cj.jdbc.Driver";

        Repo<Integer, Activitate> activitateRepo =
                new ActivitateRepo(driver, url,username, pasword);
        Repo<Integer, Activitate_grup> activitateGrupRepo =
                new ActivitateGrupRepo(driver, url,username, pasword);
        Repo<Integer, Administrator> administratorRepo =
                new AdministratorRepo(driver, url, username, pasword);
        Repo<Integer, Grup_studiu> grupStudiuRepo =
                new GrupStudiuRepo(driver, url,username, pasword);
        Repo<Integer, Materie> materieRepo =
                new MaterieRepo(driver, url,username, pasword);
        Repo<Integer, Mesaj> mesajRepo =
                new MesajRepo(driver, url,username, pasword);
        Repo<Integer, Profesor> profesorRepo =
                new ProfesorRepo(driver, url,username, pasword);
        Repo<Integer, Student> studentRepo =
                new StudentRepo(driver, url,username, pasword);
        Repo<Integer, Utilizator> utilizatorRepo =
                new UtilizatorRepo(driver, url,username, pasword);
        Repo<Tuple<Integer,Integer>, Inscriere> inscriereRepo =
                new InscriereRepo(driver,url,username, pasword);
        Repo<Tuple<Integer,Integer>, Inscriere_grup> inscriereGrupRepo =
                new InscriereGrupRepo(driver,url,username, pasword);
        Repo<Tuple<Integer,Integer>, Note_activitate> noteActivitateRepo =
                new NoteActivitateRepo(driver,url,username, pasword);
        Repo<Tuple<Integer,Integer>, Note_finale> noteFinaleRepo =
                new NoteFinaleRepo(driver,url,username, pasword);

        this.service =new Service(activitateGrupRepo,activitateRepo,administratorRepo,grupStudiuRepo,inscriereGrupRepo,
                inscriereRepo,materieRepo,mesajRepo,noteActivitateRepo,noteFinaleRepo,profesorRepo,studentRepo,utilizatorRepo);
        //System.out.println(utilizatorRepo.findOne(6).get().adresa);

        initView(stage);
        stage.setWidth(800);
        stage.show();
    }

    private void initView(Stage primaryStage) throws IOException {

        // FXMLLoader fxmlLoader = new FXMLLoader();
        //fxmlLoader.setLocation(getClass().getResource("com/example/guiex1/views/UtilizatorView.fxml"));
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("start-view.fxml"));

        AnchorPane userLayout = fxmlLoader.load();
        primaryStage.setScene(new Scene(userLayout));

        StartController startController = fxmlLoader.getController();
        startController.setService(service);

    }

    public static void main(String[] args) {
        launch();
    }
}