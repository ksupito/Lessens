package com.example.project.controller;

import com.example.project.dto.InputForm;
import com.example.project.service.UserInfoService;
import com.example.project.service.UserService;
import com.example.project.model.UserInfo;
import com.example.project.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
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
    public ModelAndView viewInput(@RequestParam(value = "error", required = false) String error, Model model) {
        return new ModelAndView("input", "lastName", new InputForm());
    }

    @RequestMapping(value = "/result", method = RequestMethod.POST)
    public String viewResult(@Valid @ModelAttribute("lastName") InputForm inputForm, BindingResult bindingResult, Model model) throws IOException {
        lastName = new String(inputForm.getLastName().getBytes("ISO-8859-1"), "UTF-8");
        if (bindingResult.hasErrors()) {
            return "input";
        }
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
            return "errors";
        }
        countPages = (int) Math.ceil((double) rowsInBase / countUsersOnePage);
        model.addAttribute("listOfUser", listOfUser);
        model.addAttribute("countPages", countPages);
        return "result";
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

