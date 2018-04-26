package com.example.project.repository;

import com.example.project.model.User;
import com.example.project.model.RegisterInfo;
import com.example.project.repository.connection.DbConnection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.sql.*;
import java.util.*;

@Repository
public class UserRepositoryImpl implements UserRepository {
    public int checkCountRows(String lastNameWasEntered) throws ClassNotFoundException, SQLException, IOException {
        int rowCount = 0;
        String sqlRequest = "SELECT COUNT(*) FROM employee where last_name LIKE ?";
        Connection connection = DbConnection.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(sqlRequest)) {
            statement.setString(1, "%" + lastNameWasEntered + "%");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                rowCount = resultSet.getInt(1);
            }
        }
        return rowCount;
    }

    public List<User> getUsers(String lastNameWasEntered, int limit, int fromIndex) throws ClassNotFoundException, SQLException, IOException {
        List<User> listOfUser = new ArrayList<>();
        String sqlRequest = "SELECT * FROM employee where last_name LIKE ? LIMIT ? OFFSET ?";
        Connection connection = DbConnection.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(sqlRequest)) {
            statement.setString(1, "%" + lastNameWasEntered + "%");
            statement.setInt(2, limit);
            statement.setInt(3, fromIndex);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String lastName = resultSet.getString("last_name");
                String firstName = resultSet.getString("first_name");
                listOfUser.add(new User(id, lastName, firstName));
            }
        }
        return listOfUser;
    }
}

