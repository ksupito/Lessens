package classes;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


public class DataBaseHelper {

    public List<User> getUsers(String lastNameWasEntered) throws ClassNotFoundException, SQLException, IOException {
        List<User> listOfUser = new ArrayList<>();
        String sqlRequest = "SELECT * FROM employee where last_name = ?";
        Connection connection = DbConnection.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(sqlRequest)) {
            statement.setString(1, lastNameWasEntered);
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
}
