import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/pageHTML")
public class HTMLPage extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter print = resp.getWriter();
        print.println("<html><head>");
        print.println("<title>A Sample Servlet!</title>");
        print.println("</head>");
        print.println("<body>");
        print.println("<h1 style=\"color:#008080\", align=\"center\">Первый servlet с HTML</h1>");
        print.println("<p><i>Первый servlet с HTML, " +
                "There was an old owl that lived in an oak. Everyday he saw incidents happening around him. " +
                "Yesterday he saw a boy helping an old man to carry a heavy basket. Today he saw a girl shouting at her mother." +
                "The more he saw the less he spoke</i></p>");
        print.println("</body></html>");
    }
}
