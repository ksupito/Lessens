package com.example.project.dto;

import com.example.project.model.Department;
import com.example.project.model.Employee;
import com.example.project.model.EmployeeInfo;

import java.util.List;

public class ObjectGenerator {
    private EmployeeInfo employeeInfo;
    private String page;
    private List<Employee> listOfEmployee;
    private Department department;

    public ObjectGenerator(Department department, EmployeeInfo employeeInfo) {
        this.department = department;
        this.employeeInfo = employeeInfo;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public ObjectGenerator(EmployeeInfo employeeInfo) {
        this.employeeInfo = employeeInfo;
    }

    public ObjectGenerator(String page) {
        this.page = page;
    }

    public EmployeeInfo getEmployeeInfo() {
        return employeeInfo;
    }

    public void setEmployeeInfo(EmployeeInfo employeeInfo) {
        this.employeeInfo = employeeInfo;
    }

    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    public List<Employee> getListOfEmployee() {
        return listOfEmployee;
    }

    public void setListOfEmployee(List<Employee> listOfEmployee) {
        this.listOfEmployee = listOfEmployee;
    }

    public ObjectGenerator(List<Employee> listOfEmployee) {
        this.listOfEmployee = listOfEmployee;
    }
}
