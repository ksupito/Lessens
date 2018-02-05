import java.sql.*;

public class DataBase1 {
    public static void main(String[] args)throws ClassNotFoundException,SQLException{
        String url = "jdbc:mysql://localhost:3306/globalProject";
        String userName = "root";
        String password = "1111";
        Class.forName("com.mysql.jdbc.Driver");
        try(Connection connection = DriverManager.getConnection(url,userName,password);
            Statement statement = connection.createStatement()){
            String lastName = "Maksimov";
            ResultSet resultSet = statement.executeQuery("SELECT * FROM employee where last_name = '" + lastName+ "'");
            while (resultSet.next()){
                System.out.println(resultSet.getString("first_name"));
            }

        }
    }

}
