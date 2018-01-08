import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/pageHTML")
public class HTMLPage extends HttpServlet {
    final String TITLE = "A Sample Servlet!";
    final String H1 = "Первый servlet с HTML";
    final String P_TAG = "Первый servlet с HTML, There was an old owl that lived in an oak. Everyday he saw " +
            "incidents happening around him. Yesterday he saw a boy helping an old man to carry a heavy basket. " +
            "Today he saw a girl shouting at her mother.he more he saw the less he spoke";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String allText = "<html><head><title>%s</title></head><body>" +
                "<h1 style=\"color:#008080\", align=\"center\">%s</h1>" +
                "<p><i>%s</i></p>" +
                "</body></html>";
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter print = resp.getWriter();
        print.println(String.format(allText, TITLE, H1, P_TAG));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        doGet(req, resp);
    }
}
