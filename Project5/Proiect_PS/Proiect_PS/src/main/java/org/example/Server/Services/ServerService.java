package org.example.Server.Services;

import org.example.Server.Model.Repository.AdministratorRepository;
import org.example.Server.Model.Repository.AngajatRepository;
import org.example.Server.Model.Repository.CoordonatorRepository;
import org.example.Server.Model.Repository.UserRepository;
import org.example.Server.Model.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ServerService {
    private UserRepository angajatRepository;
    private UserRepository coordonatorRepository;
    private UserRepository administratorRepository;

    public ServerService() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/BDtema3", "root", "root");

            angajatRepository = new AngajatRepository(connection);
            coordonatorRepository = new CoordonatorRepository(connection);
            administratorRepository = new AdministratorRepository(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User authenticateUser(String email, String password) {
        try {
            User user = angajatRepository.findUserByEmail(email);
            if (user != null && user.getPassword().equals(password)) {
                return user;
            }

            user = coordonatorRepository.findUserByEmail(email);
            if (user != null && user.getPassword().equals(password)) {
                return user;
            }

            user = administratorRepository.findUserByEmail(email);
            if (user != null && user.getPassword().equals(password)) {
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
