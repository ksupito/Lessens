package controller;

import com.google.gson.Gson;
import model.domain.InformationUser;
import model.domain.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import repository.DataBaseHelper;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@Controller
public class MainController {
    private List<User> listOfUser;
    private int countPages;
    private int rowsInBase;
    private String lastName;
    private DataBaseHelper base;
    private static final int countUsersOnePage = 3;
    private Gson gson = new Gson();
    private InformationUser informationUser = null;
    private int fromIndex = 0;
    private int idUser;
    private int numberPage = 0;
    static ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");

    @GetMapping("/login")
    public String viewLogin(@RequestParam(value = "error", required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("errorMessage", "You have to log in");
            return "login";
        }
        return "login";
    }

    @RequestMapping(value = "/input")
    public String viewInput(@RequestParam(value = "error", required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("errorMessage", "Enter valid last name!");
            return "login";
        }
        return "input";
    }

    @RequestMapping(value = "/result", method = RequestMethod.POST)
    public String viewResult(@ModelAttribute("lastName") String name, Model model) throws IOException {
        // base = new DataBaseHelper();
        base = context.getBean("base", DataBaseHelper.class);
        lastName = validate(name);
        if (lastName == null) {
            //return;
        } else {
            try {
                rowsInBase = base.checkCountRows(lastName);
                listOfUser = base.getUsers(lastName, countUsersOnePage, 0);
                if (rowsInBase == 0) {
                    return "errors";
                }
            } catch (ClassNotFoundException | SQLException e) {
                return "errors";
            } catch (IOException e) {
                //resp.sendError(404, "File not found");
                //return ;
                // return new ResponseEntity(HttpStatus.NOT_FOUND);
                //ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/404.html");
            }
            countPages = (int) Math.ceil((double) rowsInBase / countUsersOnePage);
            model.addAttribute("listOfUser", listOfUser);
            model.addAttribute("countPages", countPages);
            return "result";
        }
        return "result";
    }

    @RequestMapping(value = "/result", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    String viewResultGet(@ModelAttribute("page") String page, @ModelAttribute("id") String id, Model model, HttpServletResponse resp) {
        base = context.getBean("base", DataBaseHelper.class);
        //base = new DataBaseHelper();
        if (page != null && !page.isEmpty()) {
            numberPage = Integer.parseInt(page);
        } else {
            try {
                if (id != null) {
                    idUser = Integer.parseInt(id);
                    informationUser = base.getInformation(idUser);
                    if (informationUser != null) {
                        return gson.toJson(informationUser);
                    }
                }
            } catch (IOException | ClassNotFoundException | SQLException e) {
                return "errors";
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
            } catch (IOException | ClassNotFoundException | SQLException e) {
                return "errors";
            }
            numberPage = 0;
        }
        return gson.toJson(listOfUser);
    }

    private String validate(String name) throws IOException {
        String lastName = new String(name.getBytes("ISO-8859-1"), "UTF-8");
        if (lastName == null || lastName == "" || lastName.isEmpty()) {
            return "input";
        }
        return lastName;
    }
}
