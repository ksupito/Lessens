package com.example.project.model;

import com.example.project.utilities.ImageUtil;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;

@Cacheable
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE, region = "secondCacheEmployeeInf")
@Entity
@EntityListeners(AuditingEntityListener.class)
public class EmployeeInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    private byte[] image;

    public EmployeeInfo() {
    }

    public EmployeeInfo(String gender, int age, String department, String position, byte[] image) {
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
