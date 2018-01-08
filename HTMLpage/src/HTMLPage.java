import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/pageHTML")
public class HTMLPage extends HttpServlet {
    String title = "A Sample Servlet!";
    String h1 = "Первый servlet с HTML";
    String pTag = "Первый servlet с HTML, There was an old owl that lived in an oak. Everyday he saw " +
            "incidents happening around him. Yesterday he saw a boy helping an old man to carry a heavy basket. " +
            "Today he saw a girl shouting at her mother.he more he saw the less he spoke";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter print = resp.getWriter();
        print.println("<html><head>");
        print.println(String.format("<title>%s</title>" , title));
        print.println("</head>");
        print.println("<body>");
        print.println(String.format("<h1 style=\"color:#008080\", align=\"center\">%s</h1>", h1));
        print.println(String.format("<p><i>%s</i></p>",pTag));
        print.println("</body></html>");

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        doGet(req,resp);
    }
}
