package com.example.project.service;

import com.example.project.model.User;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface UserService {
    int checkCountRows(String lastNameWasEntered) throws ClassNotFoundException, SQLException, IOException;

    List<User> getUsers(String lastNameWasEntered, int limit, int fromIndex) throws ClassNotFoundException, SQLException, IOException;

    boolean checkUser(String login, String password) throws ClassNotFoundException, SQLException, IOException;
}
