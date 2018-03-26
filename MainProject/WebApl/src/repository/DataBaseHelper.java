package repository;

import domain.InformationUser;
import domain.User;
import utilities.ImageUtil;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper {
    public int checkCountRows(String lastNameWasEntered) throws ClassNotFoundException, SQLException, IOException {
        int rowCount = 0;
        String sqlRequest = "SELECT COUNT(*) FROM employee where last_name LIKE ?";
        Connection connection = DbConnection.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(sqlRequest)) {
            statement.setString(1, "%" + lastNameWasEntered + "%");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                rowCount = resultSet.getInt(1);
            }
        }
        return rowCount;
    }

    public List<User> getUsers(String lastNameWasEntered, int limit, int fromIndex) throws ClassNotFoundException, SQLException, IOException {
        List<User> listOfUser = new ArrayList<>();
        String sqlRequest = "SELECT * FROM employee where last_name LIKE ? LIMIT ? OFFSET ?";
        Connection connection = DbConnection.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(sqlRequest)) {
            statement.setString(1, "%" + lastNameWasEntered + "%");
            statement.setInt(2, limit);
            statement.setInt(3, fromIndex);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String lastName = resultSet.getString("last_name");
                String firstName = resultSet.getString("first_name");
                listOfUser.add(new User(id, lastName, firstName));
            }
        }
        return listOfUser;
    }

    public boolean checkUser(String login, String password) throws ClassNotFoundException, SQLException, IOException {
        Connection connection = DbConnection.getConnection();
        try (PreparedStatement statement = connection.prepareStatement("SELECT * FROM user WHERE login_name = ? and password = md5(?)")) {
            statement.setString(1, login);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            resultSet.last();
            int rows = resultSet.getRow();
            if (rows == 0) {
                return false;
            }
        }
        return true;
    }

    public InformationUser getInformation(int idUser) throws ClassNotFoundException, SQLException, IOException {
        InformationUser informationUser = null;
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
                informationUser = new InformationUser(gender, age, department, position, ImageUtil.toHexString(imageBytes));
            }
        }
        return informationUser;
    }
}

