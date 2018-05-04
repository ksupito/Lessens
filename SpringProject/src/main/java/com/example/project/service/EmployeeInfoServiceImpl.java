package com.example.project.service;

import com.example.project.model.EmployeeInfo;
import com.example.project.repository.EmployeeInfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.sql.SQLException;

@Service
public class EmployeeInfoServiceImpl implements EmployeeInfoService {
    @Autowired
    private EmployeeInfoRepository userInfo;

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS, isolation = Isolation.READ_COMMITTED)
    public EmployeeInfo getInformation(int idUser) throws ClassNotFoundException, SQLException, IOException {
        return userInfo.getInformation(idUser);
    }

}
