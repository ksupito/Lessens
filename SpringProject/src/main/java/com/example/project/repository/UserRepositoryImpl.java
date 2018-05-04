package com.example.project.repository;

import com.example.project.model.User;
import com.example.project.repository.connection.DbConnection;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@Repository
public class UserRepositoryImpl implements UserRepository {

    public User findByUsername(String login) throws ClassNotFoundException, SQLException, IOException {
        Connection connection = DbConnection.getConnection();
        User user;
        String username = null;
        String password = null;
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE username = ? ")) {
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                username = resultSet.getString("username");
                password = resultSet.getString("password");
            }
            if (username == null && password == null) {
                return null;
            } else {
                user = new User(username, password);
            }
        }
        return user;
    }
}
