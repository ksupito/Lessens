

import classes.DataBaseHelper;
import classes.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@WebServlet("/result")
public class ServletResult extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.sendRedirect("/input");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<User> listOfUser;
        DataBaseHelper base = new DataBaseHelper();
        String lastName = validate(req, resp);
        if (lastName == null) {
            return;
        } else {
            try {
                listOfUser = base.getUsers(lastName);
                if (listOfUser.size() == 0) {
                    resp.sendRedirect("/input?error=true");
                    return;
                }
            } catch (ClassNotFoundException | SQLException e) {
                req.getRequestDispatcher("jsp/errors.jsp").forward(req, resp);
                return;
            } catch (IOException e) {
                resp.sendError(404, "File not found");
                return;
            }
            req.setAttribute("listOfUser", listOfUser);
            req.getRequestDispatcher("jsp/result.jsp").forward(req, resp);
        }
    }

    private boolean isString(String testString) {
        Pattern p = Pattern.compile("^[а-яА-ЯёЁa-zA-Z]+$");
        Matcher m = p.matcher(testString);
        return m.matches();
    }

    private String validate(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String lastNameRequest = req.getParameter("lastName").trim();
        String lastName = new String(lastNameRequest.getBytes("ISO-8859-1"), "UTF-8");
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
}
