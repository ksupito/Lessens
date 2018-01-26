package newProject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;

@WebServlet("/output")
public class ServletForOutputPage extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        try {
            setAttributeOfSession(req, session);
        } catch (NumberFormatException e) {
            req.setAttribute("errorMessage", "Invalid time");
            req.getRequestDispatcher("jsp/EntryPage.jsp").forward(req, resp);
        }
        startJSP(req, resp, session);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    private void setAttributeOfSession(HttpServletRequest req, HttpSession session) throws NumberFormatException {
        String timeOfSession = req.getParameter("timeOfSession");
        String valueOfSession = req.getParameter("valueOfSession");
        if (timeOfSession != null && timeOfSession != "" && valueOfSession != null && valueOfSession != "") {
            int sessionTime = Integer.parseInt(timeOfSession);
            session.setAttribute("timeOfSession", timeOfSession);
            session.setAttribute("valueOfSession", valueOfSession);
            session.setMaxInactiveInterval(sessionTime);
        }
    }

    private void startJSP(HttpServletRequest req, HttpServletResponse resp, HttpSession session) throws ServletException, IOException {
        if (session.isNew() == true) {
            req.setAttribute("cancelMessage", "Session was ended");
            req.getRequestDispatcher("jsp/EntryPage.jsp").forward(req, resp);
        } else if ((session.getAttribute("timeOfSession") == null || Integer.parseInt(session.getAttribute("timeOfSession").toString()) <= 0)
                && session.isNew() == false) {
            req.setAttribute("errorMessage", "Invalid time");
            req.getRequestDispatcher("jsp/EntryPage.jsp").forward(req, resp);
        } else {
            req.getRequestDispatcher("jsp/OutputPage.jsp").forward(req, resp);
        }
    }
}
