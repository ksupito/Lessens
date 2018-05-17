package com.example.project.model;

import com.example.project.utilities.ImageUtil;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class EmployeeInfo implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "employee_id")
    @NotNull
    private int employeeId;
    @Column
    @NotNull
    private String gender;
    @Column
    @Max(200)
    private int age;
    @Column
    @NotNull
    private String division;
    @Column
    @NotNull
    private String position;
    @Column
    @NotNull
    private byte[] image;

    public EmployeeInfo() {
    }

    public EmployeeInfo(String gender, int age, String division, String position, byte[] image) {
        this.gender = gender;
        this.age = age;
        this.division = division;
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

    public String getDivision() {
        return division;
    }

    public void setDepartment(String division) {
        this.division = division;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getImage() {
        return ImageUtil.toHexString(image);
    }

    public void setImage(byte[] image) {
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
