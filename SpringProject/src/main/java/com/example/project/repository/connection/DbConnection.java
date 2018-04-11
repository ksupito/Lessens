package com.example.project.repository.connection;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DbConnection {
    private static String url;
    private static String login;
    private static String password;
    private static String driver;
    private static Connection connection;

    public static Connection getConnection() throws ClassNotFoundException, SQLException, IOException {
        if (connection == null) {
            getProperty();
            Class.forName(driver);
            connection = DriverManager.getConnection(url, login, password);
        }
        return connection;
    }

    private static void getProperty() throws IOException {
        Properties property = new Properties();
        try (InputStream is = DbConnection.class.getResourceAsStream("/config.properties")) {
            property.load(is);
            url = property.getProperty("dburl");
            login = property.getProperty("dblogin");
            password = property.getProperty("dbpassword");
            driver = property.getProperty("dbdriver");
        }
    }
}
