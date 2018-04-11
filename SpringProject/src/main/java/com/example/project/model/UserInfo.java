package com.example.project.model;

public class UserInfo {
    private String gender;
    private int age;
    private String department;
    private String position;
    private String image;

    public UserInfo(String gender, int age, String department, String position, String image) {
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
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
