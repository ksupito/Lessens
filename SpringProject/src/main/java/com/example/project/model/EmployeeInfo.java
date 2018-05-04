package com.example.project.model;


import javax.persistence.*;


import java.io.Serializable;
import java.sql.Blob;

@Entity
@Table(name = "information")
public class EmployeeInfo implements Serializable {
    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "employee_id")
    private int employeeId;
    @Column
    private String gender;
    @Column
    private int age;
    @Column
    private String department;
    @Column
    private String position;
    @Column
    Blob image;
    //private byte[] image;

    public EmployeeInfo() {
    }

    public EmployeeInfo(String gender, int age, String department, String position, Blob image) {
        this.gender = gender;
        this.age = age;
        this.department = department;
        this.position = position;
        this.image = image;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Blob getImage() {
        return image;
    }

    public void setImage(Blob image) {
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }
}
