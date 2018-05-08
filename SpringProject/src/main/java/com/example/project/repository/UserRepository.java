package com.example.project.repository;

import com.example.project.model.User;

import java.io.IOException;
import java.sql.SQLException;

public interface UserRepository {
    User findByUsername(String login);
}
