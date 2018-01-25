package newProject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/main2")
public class ServletWithSession extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        while (true) {
            try {
                String timeOfSession = req.getParameter("timeOfSession");
                String valueOfSession = req.getParameter("valueOfSession");

                if (timeOfSession != null && timeOfSession != "" && valueOfSession != null && valueOfSession != "") {
                    session.setAttribute("timeOfSession", timeOfSession);
                    session.setAttribute("valueOfSession", valueOfSession);
                    int a = Integer.parseInt(timeOfSession);
                    session.setMaxInactiveInterval(a);
                }
            } catch (NumberFormatException e) {
                req.setAttribute("errorMessage", "Invalid time");
                req.getRequestDispatcher("jsp/FirstPage.jsp").forward(req, resp);
            } catch (IllegalStateException e) {
            }
            req.getRequestDispatcher("jsp/SecondPage.jsp").forward(req, resp);
            break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
