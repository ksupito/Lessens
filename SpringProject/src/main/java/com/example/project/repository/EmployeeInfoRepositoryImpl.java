package com.example.project.repository;

import com.example.project.model.EmployeeInfo;
import com.example.project.repository.connection.DbConnection;
import com.example.project.utilities.HibernateUtil;
import com.example.project.utilities.ImageUtil;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.io.IOException;
import java.sql.*;

@Repository
public class EmployeeInfoRepositoryImpl implements EmployeeInfoRepository {
    /*public EmployeeInfo getInformation(int idUser) throws ClassNotFoundException, SQLException, IOException {
        EmployeeInfo employeeInfo = null;
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
                employeeInfo = new EmployeeInfo(gender, age, department, position, ImageUtil.toHexString(imageBytes));
            }
        }
        return employeeInfo;
    }*/

    public EmployeeInfo getInformation(int idUser) throws ClassNotFoundException, SQLException, IOException {
        EntityManager entityMgr = HibernateUtil.getEntityManagerFactory().createEntityManager();
        entityMgr.getTransaction().begin();
        EmployeeInfo employeeInfo = entityMgr.find(EmployeeInfo.class, 1);   //<- вот прям тут
        return employeeInfo;
    }
}
