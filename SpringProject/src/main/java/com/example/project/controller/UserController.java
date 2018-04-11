package com.example.project.controller;

import com.example.project.service.UserInfoService;
import com.example.project.service.UserService;
import com.example.project.utilities.ValidateUtil;
import com.example.project.model.UserInfo;
import com.example.project.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserInfoService userInfoService;
    private List<User> listOfUser;
    private int countPages;
    private int rowsInBase;
    private String lastName;
    private static final int countUsersOnePage = 3;
    private UserInfo userInfo = null;
    private int fromIndex = 0;
    private int idUser;
    private int numberPage = 0;

    @RequestMapping(value = "/input")
    public String viewInput(@RequestParam(value = "error", required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("errorMessage", "Enter valid last name!");
            return "input";
        }
        return "input";
    }

    @RequestMapping(value = "/result", method = RequestMethod.POST)
    public String viewResult(@ModelAttribute("lastName") String name, Model model) throws IOException {
        lastName = ValidateUtil.validate(name);
        if (lastName != null) {
            try {
                rowsInBase = userService.checkCountRows(lastName);
                listOfUser = userService.getUsers(lastName, countUsersOnePage, 0);
                if (rowsInBase == 0) {
                    model.addAttribute("errorMessage", "There are no coincidences!");
                    return "input";
                }
            } catch (ClassNotFoundException | SQLException e) {
                return "errors";
            } catch (IOException e) {
                //return "meters/notfound";
                //resp.sendError(404, "File not found");
                //return ;
                //return (HttpStatus.NOT_FOUND);
                //ErrorPage error404Page = new ErrorPage(HttpStatus.NOT_FOUND, "/404.html");
            }
            countPages = (int) Math.ceil((double) rowsInBase / countUsersOnePage);
            model.addAttribute("listOfUser", listOfUser);
            model.addAttribute("countPages", countPages);
            return "result";
        } else {
            return "input";
        }
    }

    @RequestMapping(value = "/result", method = RequestMethod.GET, produces = "application/json")
    public @ResponseBody
    Object viewResultGet(@ModelAttribute("page") String page, @ModelAttribute("id") String id, Model model, HttpServletResponse resp) {
        if (page != null && !page.isEmpty()) {
            numberPage = Integer.parseInt(page);
        } else {
            try {
                if (id != null) {
                    idUser = Integer.parseInt(id);
                    userInfo = userInfoService.getInformation(idUser);
                    if (userInfo != null) {
                        return userInfo;
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
                listOfUser = userService.getUsers(lastName, countUsersOnePage, fromIndex);
            } catch (IOException | ClassNotFoundException | SQLException e) {
                return "errors";
            }
            numberPage = 0;
        }
        return listOfUser;
    }
}

