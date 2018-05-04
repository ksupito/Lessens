package com.example.project.service;

import com.example.project.model.EmployeeInfo;


import java.io.IOException;
import java.sql.SQLException;

public interface EmployeeInfoService {
    EmployeeInfo getInformation(int idUser) throws ClassNotFoundException, SQLException, IOException;
}
