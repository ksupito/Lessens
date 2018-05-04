package com.example.project.repository;

import com.example.project.model.Employee;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface EmployeeRepository {
    int checkCountRows(String lastNameWasEntered) throws ClassNotFoundException, SQLException, IOException;

    List<Employee> getUsers(String lastNameWasEntered, int limit, int fromIndex) throws ClassNotFoundException, SQLException, IOException;
}
