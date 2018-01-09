import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

@WebServlet("/timeServlet")
public class TimeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Date date = new Date();
        String allText = "<html><head><title>Title</title></head><body>" +
                "<h1>Время : </h1>" +
                "<p>%s</p>" +
                "</body></html>";
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter print = resp.getWriter();
        print.println(String.format(allText, date.toString()));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        doGet(req, resp);
    }
}
