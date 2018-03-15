import classes.DataBaseHelper;
import classes.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/result")
public class ServletResult extends HttpServlet {
    private List<User> listOfUser;
    private int countPages;
    private int linesInBase;
    private String lastName;
    private DataBaseHelper base;
    private static final int countUsersOnePage = 3;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int minIndex;
        DataBaseHelper base = new DataBaseHelper();
        int numberPage = Integer.parseInt(req.getParameter("page"));
        if (numberPage == 1) {
            minIndex = numberPage - 1;
        } else {
            if (listOfUser.size() % countUsersOnePage != 0 && numberPage == countPages) {
                minIndex = numberPage * countUsersOnePage - countUsersOnePage;
            } else {
                minIndex = numberPage * countUsersOnePage - countUsersOnePage;
            }
        }
        try {
            listOfUser = base.getUsers(lastName, countUsersOnePage, minIndex);
        } catch (ClassNotFoundException | SQLException e) {
            req.getRequestDispatcher("jsp/errors.jsp").forward(req, resp);
            return;
        }
        req.setAttribute("listOfUser", listOfUser);
        req.setAttribute("countPages", countPages);
        req.getRequestDispatcher("jsp/result.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        base = new DataBaseHelper();
        lastName = validate(req, resp);
        if (lastName == null) {
            return;
        } else {
            try {
                linesInBase = base.checkCountPages(lastName);
                listOfUser = base.getUsers(lastName, countUsersOnePage, 0);
                if (linesInBase == 0) {
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
            countPages = (int) Math.ceil((double) linesInBase / countUsersOnePage);
            req.setAttribute("listOfUser", listOfUser); ////
            req.setAttribute("countPages", countPages);
            req.getRequestDispatcher("jsp/result.jsp").forward(req, resp);
        }
    }

    private String validate(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String lastNameRequest = req.getParameter("lastName").trim();
        String lastName = new String(lastNameRequest.getBytes("ISO-8859-1"), "UTF-8");
        if (lastName == null || lastName == "" || lastName.isEmpty()) {
            resp.sendRedirect("/input?error=true");
            return null;
        }
        return lastName;
    }
}
