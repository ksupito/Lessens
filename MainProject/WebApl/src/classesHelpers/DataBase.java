package classesHelpers;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class DataBase {
    private String url;
    private String login;
    private String password;
    private String driver;
    private static Connection connection;

    public List<User> takeData(String lastNameWasEntered) throws ClassNotFoundException, SQLException, IOException, NullPointerException {
        List<User> listOfUser = new ArrayList<>();
        if (connection == null) {
            try {
                getProperty();
                Class.forName(driver);
                connection = DriverManager.getConnection(url, login, password);

            } catch (FileNotFoundException e) {
                System.out.println(e);
            }
        }
        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * FROM employee where last_name = '" + lastNameWasEntered + "'");

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String lastName = resultSet.getString("last_name");
                String firstName = resultSet.getString("first_name");
                listOfUser.add(new User(id, lastName, firstName));
            }
        }
        return listOfUser;
    }

    private void getProperty() throws IOException {
        Properties property = new Properties();
        try (InputStream is = new FileInputStream("src\\resources\\config.properties")) {
            property.load(is);
            url = property.getProperty("dburl");
            login = property.getProperty("dblogin");
            password = property.getProperty("dbpassword");
            driver = property.getProperty("dbdriver");
        }
    }
}
