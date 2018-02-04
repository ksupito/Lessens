import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet("/result")
public class ServletResult extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("/input");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> listOfUser;
        String lastName = validate(req, resp);
        if (lastName == null) {
            return;
        } else {
            try {
                listOfUser = getData(lastName);
                if (listOfUser.size() == 0) {
                    resp.sendRedirect("/input?error=true");
                    return;
                }
            } catch (ClassNotFoundException | SQLException e) {
                resp.sendError(523, "Origin Is Unreachable");
                return;
            }
            req.setAttribute("listOfUser", listOfUser);
            req.getRequestDispatcher("jsp/result.jsp").forward(req, resp);
        }
    }

    private static boolean isString(String testString) {
        Pattern p = Pattern.compile("^[а-яА-ЯёЁa-zA-Z]+$");
        Matcher m = p.matcher(testString);
        return m.matches();
    }

    private String validate(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        req.setCharacterEncoding("UTF-8");
        String lastName = req.getParameter("lastName").trim();
        if (lastName == null || lastName == "" || lastName.isEmpty()) {
            resp.sendRedirect("/input?error=true");
            return null;
        }
        if (isString(lastName) != true) {
            resp.sendRedirect("/input?error=true");
            return null;
        }
        return lastName;
    }

    private List<User> getData(String lastNameWasEntered) throws ClassNotFoundException, SQLException {
        String url = "jdbc:mysql://localhost:3306/globalProject";
        String memberName = "root";
        String memberPassword = "1111";
        List<User> listOfUser = new ArrayList<>();

        Class.forName("com.mysql.jdbc.Driver");
        try (Connection connection = DriverManager.getConnection(url, memberName, memberPassword);
             Statement statement = connection.createStatement()) {
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


}
