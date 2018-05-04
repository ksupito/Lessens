package com.example.project.service;

import com.example.project.model.Employee;
import com.example.project.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private EmployeeRepository base;

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS, isolation = Isolation.READ_COMMITTED)
    public int checkCountRows(String lastNameWasEntered) throws ClassNotFoundException, SQLException, IOException {
        return base.checkCountRows(lastNameWasEntered);
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS, isolation = Isolation.READ_COMMITTED)
    public List<Employee> getUsers(String lastNameWasEntered, int limit, int fromIndex) throws ClassNotFoundException, SQLException, IOException {
        return base.getUsers(lastNameWasEntered, limit, fromIndex);
    }
}
