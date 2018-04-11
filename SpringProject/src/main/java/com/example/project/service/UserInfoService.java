package com.example.project.service;

import com.example.project.model.UserInfo;

import java.io.IOException;
import java.sql.SQLException;

public interface UserInfoService {
    UserInfo getInformation(int idUser) throws ClassNotFoundException, SQLException, IOException;
}
