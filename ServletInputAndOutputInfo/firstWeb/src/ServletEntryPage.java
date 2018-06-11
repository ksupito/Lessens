package newProject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/entry")
public class ServletEntryPage extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getParameter("error") != null) {
            req.setAttribute("errorMessage", "Invalid time");
            req.getRequestDispatcher("jsp/entryPage.jsp").forward(req, resp);
        } else if (req.getParameter("session") != null) {
            req.setAttribute("sessionCanceledMessage", "Session was ended");
            req.getRequestDispatcher("jsp/entryPage.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("jsp/entryPage.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
