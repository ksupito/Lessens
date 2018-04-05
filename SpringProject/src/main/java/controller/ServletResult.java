package controller;

import config.MainConfig;
import context.ApplicationContextProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import repository.DataBaseHelper;
import model.domain.InformationUser;
import model.domain.User;
import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.List;

@Component
public class ServletResult extends HttpServlet {
    @Autowired
    private DataBaseHelper base;
    private List<User> listOfUser;
    private int countPages;
    private int rowsInBase;
    private String lastName;
    private static final int countUsersOnePage = 3;
    private PrintWriter printWriter;
    private Gson gson = new Gson();
    private InformationUser informationUser = null;
    private int fromIndex = 0;
    private int idUser;
    private int numberPage = 0;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id;
        String page;
        base = ApplicationContextProvider.getContext().getBean("dataBaseHelper", DataBaseHelper.class);
        printWriter = resp.getWriter();
        resp.setContentType("text/plain");
        page = req.getParameter("page");
        if (page != null) {
            numberPage = Integer.parseInt(page);
        } else {
            try {
                id = req.getParameter("id");
                if (id != null) {
                    idUser = Integer.parseInt(id);
                    informationUser = base.getInformation(idUser);
                    if (informationUser != null) {
                        printWriter.print(gson.toJson(informationUser));
                    }
                }
            } catch (ClassNotFoundException | SQLException e) {
                req.getRequestDispatcher("jsp/errors.jsp").forward(req, resp);
                return;
            }
        }
        if (numberPage == 1) {
            fromIndex = numberPage - 1;
        } else if (numberPage != 0) {
            if (listOfUser.size() % countUsersOnePage != 0 && numberPage == countPages) {
                fromIndex = numberPage * countUsersOnePage - countUsersOnePage;
            } else {
                fromIndex = numberPage * countUsersOnePage - countUsersOnePage;
            }
        }
        if (numberPage != 0) {
            try {
                listOfUser = base.getUsers(lastName, countUsersOnePage, fromIndex);
            } catch (ClassNotFoundException | SQLException e) {
                req.getRequestDispatcher("jsp/errors.jsp").forward(req, resp);
                return;
            }
            numberPage = 0;
            printWriter.print(gson.toJson(listOfUser));
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        base = ApplicationContextProvider.getContext().getBean("dataBaseHelper", DataBaseHelper.class);
        lastName = validate(req, resp);
        if (lastName == null) {
            return;
        } else {
            try {
                rowsInBase = base.checkCountRows(lastName);
                listOfUser = base.getUsers(lastName, countUsersOnePage, 0);
                if (rowsInBase == 0) {
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
            countPages = (int) Math.ceil((double) rowsInBase / countUsersOnePage);
            req.setAttribute("listOfUser", listOfUser);
            req.setAttribute("countPages", countPages);
            req.getRequestDispatcher("WEB-INF/jsp/result.jsp").forward(req, resp);
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