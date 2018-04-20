package com.example.project.controller;

import com.example.project.dto.InputForm;
import com.example.project.dto.ObjectGenerator;
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

import javax.validation.Valid;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@Controller
@SessionAttributes({"lastName", "countPages", "listOfUser"})
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserInfoService userInfoService;
    private static final int countUsersOnePage = 3;

    @RequestMapping(value = "/input")
    public ModelAndView viewInput(@RequestParam(value = "error", required = false) String error, Model model) {
        return new ModelAndView("input", "lastName", new InputForm());
    }

    @RequestMapping(value = "/result", method = RequestMethod.POST)
    public String viewResultPostRequest(@Valid @ModelAttribute("lastName") InputForm inputForm, BindingResult bindingResult, Model model) {
        int rowsInBase;
        List<User> listOfUser;
        String lastName = inputForm.getLastName();
        if (bindingResult.hasErrors()) {
            return "input";
        }
        try {
            rowsInBase = userService.checkCountRows(lastName);
            listOfUser = userService.getUsers(lastName, countUsersOnePage, 0);
            if (rowsInBase == 0) {
                model.addAttribute("errorMessage", "noCoincidencesError");
                return "input";
            }
        } catch (ClassNotFoundException | SQLException | IOException e) {
            return "errors";
        }
        int countPages = (int) Math.ceil((double) rowsInBase / countUsersOnePage);
        model.addAttribute("listOfUser", listOfUser);
        model.addAttribute("countPages", countPages);
        return "result";
    }

    @RequestMapping(value = "/result", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ObjectGenerator viewResultGetRequest(@ModelAttribute("lastName") InputForm inputForm,
                                                @ModelAttribute("countPages") int countPages,
                                                @ModelAttribute("page") String page,
                                                @ModelAttribute("id") String id,
                                                @ModelAttribute("listOfUser") List<User> listOfUser) {
        String lastName = inputForm.getLastName();
        int numberPage = 0;
        int idUser;
        int fromIndex = 0;
        if (page != null && !page.isEmpty()) {
            numberPage = Integer.parseInt(page);
        } else {
            try {
                if (id != null) {
                    idUser = Integer.parseInt(id);
                    UserInfo userInfo = userInfoService.getInformation(idUser);
                    if (userInfo != null) {
                        return new ObjectGenerator(userInfo);
                    }
                }
            } catch (IOException | ClassNotFoundException | SQLException e) {
                //return "errors";
                return new ObjectGenerator("errors"); //
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
                numberPage = 0;
            } catch (IOException | ClassNotFoundException | SQLException e) {
                //return "errors";
                return new ObjectGenerator("errors"); //
            }
        }
        return new ObjectGenerator(listOfUser);
    }
}

