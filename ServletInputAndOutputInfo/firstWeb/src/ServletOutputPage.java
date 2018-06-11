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
public class ServletOutputPage extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        startJSP(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        try {
            if (setAttributeOfSession(req, resp, session) == false) {
                return;
            }
        } catch (NumberFormatException e) {
            resp.sendRedirect("/entry?error=true");
            return;
        }
        startJSP(req, resp);
    }

    private boolean setAttributeOfSession(HttpServletRequest req, HttpServletResponse resp, HttpSession session) throws NumberFormatException, IOException {
        String timeOfSession = req.getParameter("timeOfSession");
        String valueOfSession = req.getParameter("valueOfSession");
        if (Integer.parseInt(timeOfSession) <= 0 || valueOfSession == null || valueOfSession.trim().isEmpty()) {
            resp.sendRedirect("/entry?error=true");
            return false;
        }
        int sessionTime = Integer.parseInt(timeOfSession);
        session.setAttribute("timeOfSession", timeOfSession);
        session.setAttribute("valueOfSession", valueOfSession);
        session.setMaxInactiveInterval(sessionTime);
        return true;
    }

    private void startJSP(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession(false) == null) {
            resp.sendRedirect("/entry?session=ended");
        } else {
            req.getRequestDispatcher("jsp/outputPage.jsp").forward(req, resp);
        }
    }
}
