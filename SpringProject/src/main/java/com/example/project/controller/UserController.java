package com.example.project.controller;

import com.example.project.dto.InputForm;
import com.example.project.dto.ObjectGenerator;
import com.example.project.model.Department;
import com.example.project.model.Employee;
import com.example.project.service.DepartmentService;
import com.example.project.service.EmployeeInfoService;
import com.example.project.service.EmployeeService;
import com.example.project.model.EmployeeInfo;
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
public class UserController {
    private static final int COUNT_USERS_ONE_PAGE = 3;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private EmployeeInfoService employeeInfoService;
    @Autowired
    private DepartmentService departmentService;

    @RequestMapping(value = "/input")
    public ModelAndView viewUsers() {
        return new ModelAndView("input", "lastName", new InputForm());
    }

    @RequestMapping(value = "/result", method = RequestMethod.GET)
    public String viewUsers(@Valid @ModelAttribute("lastName") InputForm inputForm, BindingResult bindingResult, Model model) {
        int rowsInBase;
        List<Employee> listOfEmployee;
        String lastName = inputForm.getLastName();
        if (bindingResult.hasErrors()) {
            return "input";
        }
        try {
            rowsInBase = employeeService.checkCountRows(lastName);
            listOfEmployee = employeeService.getUsers(lastName, COUNT_USERS_ONE_PAGE, 0);
            if (rowsInBase == 0) {
                model.addAttribute("errorMessage", "noCoincidencesError");
                return "input";
            }
        } catch (ClassNotFoundException | SQLException | IOException e) {
            return "errors";
        }
        int countPages = (int) Math.ceil((double) rowsInBase / COUNT_USERS_ONE_PAGE);
        model.addAttribute("listOfEmployee", listOfEmployee);
        model.addAttribute("countPages", countPages);
        model.addAttribute("lastName", lastName);
        return "result";
    }

    @RequestMapping(value = "/moreUsers", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ObjectGenerator viewMoreUsers(@ModelAttribute(value = "lastName") String lastName,
                                         @ModelAttribute("countPages") int countPages,
                                         @ModelAttribute("page") String page) {
        int fromIndex;
        List<Employee> listOfEmployee;
        int numberPage = Integer.parseInt(page);
        if (numberPage == 1) {
            fromIndex = numberPage - 1;
        } else {
            if (numberPage == countPages) {
                fromIndex = numberPage * COUNT_USERS_ONE_PAGE - COUNT_USERS_ONE_PAGE;
            } else {
                fromIndex = numberPage * COUNT_USERS_ONE_PAGE - COUNT_USERS_ONE_PAGE;
            }
        }
        try {
            listOfEmployee = employeeService.getUsers(lastName, COUNT_USERS_ONE_PAGE, fromIndex);
        } catch (IOException | ClassNotFoundException | SQLException e) {
            return new ObjectGenerator("errors"); //
        }
        return new ObjectGenerator(listOfEmployee);
    }

    @RequestMapping(value = "/details", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ObjectGenerator viewPopup(@ModelAttribute("id") String id) {
        EmployeeInfo employeeInfo;
        Department department =null;
        int idUser = Integer.parseInt(id);
        try {
            employeeInfo = employeeInfoService.getInformation(idUser);
            department = departmentService.getDepartment(idUser);
        } catch (IOException | ClassNotFoundException | SQLException e) {
            return new ObjectGenerator("errors");
        }
        return new ObjectGenerator(department, employeeInfo);
    }
}

