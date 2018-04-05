package model.domain;

public class InformationUser {
    private String gender;
    private int age;
    private String department;
    private String position;
    private String image;

    public InformationUser(String gender, int age, String department, String position, String image) {
        this.gender = gender;
        this.age = age;
        this.department = department;
        this.position = position;
        this.image = image;
    }
}
