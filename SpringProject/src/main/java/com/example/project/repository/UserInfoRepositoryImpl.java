package com.example.project.repository;

import com.example.project.model.UserInfo;
import com.example.project.repository.connection.DbConnection;
import com.example.project.utilities.ImageUtil;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.sql.*;

@Repository
public class UserInfoRepositoryImpl implements UserInfoRepository {

    public UserInfo getInformation(int idUser) throws ClassNotFoundException, SQLException, IOException {
        UserInfo userInfo = null;
        String sqlRequest = "SELECT * FROM information where employee_id = ? ";
        Connection connection = DbConnection.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(sqlRequest)) {
            statement.setInt(1, idUser);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int age = resultSet.getInt("age");
                String gender = resultSet.getString("gender");
                String department = resultSet.getString("department");
                String position = resultSet.getString("position");
                Blob imageBlob = resultSet.getBlob("image");
                byte[] imageBytes = imageBlob.getBytes(1, (int) imageBlob.length());
                userInfo = new UserInfo(gender, age, department, position, ImageUtil.toHexString(imageBytes));
            }
        }
        return userInfo;
    }
}
