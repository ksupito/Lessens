package com.example.project.repository;

import com.example.project.model.UserInfo;

import java.io.IOException;
import java.sql.SQLException;

public interface UserInfoRepository {
    UserInfo getInformation(int idUser) throws ClassNotFoundException, SQLException, IOException;
}
