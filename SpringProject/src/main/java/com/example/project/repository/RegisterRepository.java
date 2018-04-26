package com.example.project.repository;

import com.example.project.model.RegisterInfo;

import java.io.IOException;
import java.sql.SQLException;

public interface RegisterRepository {
    RegisterInfo findByUsername(String login) throws ClassNotFoundException, SQLException, IOException;
}
