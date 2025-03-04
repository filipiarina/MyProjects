package org.example.Server.Model.Repository;

import org.example.Server.Model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserRepository {
    User findUserByEmail(String email) throws SQLException;
    List<User> getAllUsers() throws SQLException;
}
