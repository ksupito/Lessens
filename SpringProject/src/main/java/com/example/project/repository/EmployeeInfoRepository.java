package com.example.project.repository;

import com.example.project.model.EmployeeInfo;

import java.io.IOException;
import java.sql.SQLException;

public interface EmployeeInfoRepository {
    EmployeeInfo getInformation(int idUser) throws ClassNotFoundException, SQLException, IOException;
}
